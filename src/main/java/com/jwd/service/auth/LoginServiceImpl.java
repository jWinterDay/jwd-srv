package com.jwd.service.auth;

import com.jwd.exception.CustomException;
import com.jwd.model.auth.Login;
import com.jwd.model.auth.LoginResponse;
import com.jwd.model.auth.User;
import com.jwd.repository.auth.ILoginRepository;
import com.jwd.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginRepository loginRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${security.password.secret-key}")
    private String passwordSecretKey = "6d2ba458bf1d45bdb19eb74547eae9bd";

    @Override
    public User findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public int updateRefreshTokenByEmail(String refreshToken, String email) {
        return loginRepository.updateRefreshTokenByEmail(refreshToken, email);
    }

    @Override
    public LoginResponse login(Login loginForm) {
        User user = loginRepository.findByEmail(loginForm.getEmail());

        if (user == null) {
            throw new CustomException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(passwordSecretKey, 10000, 128);

        String dbHash = user.getPasswordHash();
        boolean isMatches = encoder.matches(loginForm.getPassword(), dbHash);

        if (!isMatches) {
            throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String token = jwtTokenProvider.createToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        //update refresh token in database
        loginRepository.updateRefreshTokenByEmail(refreshToken, loginForm.getEmail());

        LoginResponse loginResponse = new LoginResponse(token, refreshToken);

        return loginResponse;
    }

    @Override
    public String refreshToken(HttpServletRequest request,
                               HttpServletResponse response) {
        String currentToken = jwtTokenProvider.resolveToken(request);
        String email = jwtTokenProvider.getSubject(currentToken);

        User user = loginRepository.findByEmail(email);

        if (user == null) {
            throw new CustomException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String token = jwtTokenProvider.createToken(user);

        return token;
    }

    @PostConstruct
    public void postInit() {
        System.out.println("postInit");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
    }
}
