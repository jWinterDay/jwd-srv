package com.jwd.security;

import com.jwd.exception.CustomException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    //constructor
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (CustomException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }*/

        /*String xHeader = ((HttpServletRequest)request).getHeader("X-Auth-Token");
        if(getPermission(xHeader)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("401.html").forward(request, response);
        }*/

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        httpServletRequest.getRequestDispatcher("/project_list?page=0&size=4").forward(httpServletRequest, httpServletResponse);
    }
}
