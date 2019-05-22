package com.jwd.security;

import com.google.gson.Gson;
import com.jwd.exception.CustomException;
import com.jwd.model.auth.AccessGroup;
import com.jwd.model.auth.User;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RequestInterceptor implements HandlerInterceptor {
    private JwtTokenProvider jwtTokenProvider;

    public RequestInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //This method is called before the controller
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = jwtTokenProvider.resolveToken(request);

        boolean isValid = jwtTokenProvider.validateToken(token);
        if (!isValid) { return false; }

        HandlerMethod method = (HandlerMethod) handler;
        RequestAccess requestAccessGroup = method.getMethodAnnotation(RequestAccess.class);
        if (requestAccessGroup != null) {
            User user = jwtTokenProvider.parse(token);

            long expectedId = requestAccessGroup.accessGroupId();
            List<AccessGroup> userGroups = user.getAccessGroups();

            if (!userGroups.contains(expectedId)) {
                throw new CustomException("You don't have permission", HttpStatus.FORBIDDEN);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
