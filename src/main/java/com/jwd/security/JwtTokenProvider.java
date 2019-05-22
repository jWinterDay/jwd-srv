package com.jwd.security;


import com.google.gson.Gson;
import com.jwd.exception.CustomException;

import com.jwd.model.auth.AccessGroup;
import com.jwd.model.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtTokenProvider {
    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.token.signing-key}")
    private String tokenSecretKey;
    @Value("${security.token.expiration-time-ms}")
    private long tokenValidityInMilliseconds = 2800000;

    @Value("${security.refresh-token.signing-key}")
    private String refreshTokenSecretKey;
    @Value("${security.refresh-token.expiration-time-ms}")
    private long refreshTokenValidityInMilliseconds = 432000000;

    @PostConstruct
    protected void init() {
        tokenSecretKey = Base64.getEncoder().encodeToString(tokenSecretKey.getBytes());
    }

    public String createToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);

        Claims claims = Jwts.claims();//.setSubject(user.getEmail());
        claims.put("email", user.getEmail());
        claims.put("first_name", user.getFirstName());
        claims.put("last_name", user.getLastName());
        claims.put("access_groups", user.getAccessGroups());


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, tokenSecretKey)
                .compact();
    }

    public String createRefreshToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, refreshTokenSecretKey)
                .compact();
    }

    public User parse(String token) {
        final Claims claims = Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token).getBody();

        User user = new User();
        user.setEmail(claims.get("email").toString());
        user.setFirstName(claims.get("first_name").toString());
        user.setLastName(claims.get("last_name").toString());

        List<Map> groups = claims.get("access_groups", ArrayList.class);
        List<AccessGroup> accessGroups = groups.stream()
                .map(p -> new AccessGroup(Long.parseLong(p.get("access_group_id").toString()), p.get("name").toString()))
                .collect(Collectors.toList());

        user.setAccessGroups(accessGroups);
        //List<Long> userGroupIds = groups.stream()
        //        .map(p -> Long.parseLong(p.get("access_group_id").toString()))
        //        .collect(Collectors.toList());

        return user;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        if (token == null) {
            throw new CustomException("invalid JWT token", HttpStatus.UNAUTHORIZED);
        }

        try {
            Jwts.parser().setSigningKey(tokenSecretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED);
        }
    }
}
