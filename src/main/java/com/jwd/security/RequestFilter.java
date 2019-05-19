package com.jwd.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwd.exception.CustomException;
import com.jwd.model.ResponseMsg;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

//@Component
//@Configuration
@WebFilter(urlPatterns = "/yoo")
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> tt = filterConfig.getInitParameterNames();
        System.out.println("RequestFilter init. tt =" + tt + ", n = " + filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("RequestFilter doFilter");

        HttpServletRequest req = (HttpServletRequest) request;

        String servletPath = req.getServletPath();

        System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath + ", URL =" + req.getRequestURL());

        //throw new CustomException("<><><><>", HttpStatus.UNAUTHORIZED);

        // Разрешить request продвигаться дальше. (Перейти данный Filter).
        //chain.doFilter(request, response);

        //if (req.getHeader("x-dawson-nonce") == null || req.getHeader("x-dawson-signature") == null) {
            //HttpServletResponse httpResponse = (HttpServletResponse) response;
            //httpResponse.setContentType("application/json");
            //httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required headers not specified in the request");
            //return;
        //}

        //chain.doFilter(request, response);
        //request.getRequestDispatcher("error.html").forward(request, response);

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json");

        ResponseMsg resp = new ResponseMsg("unauth", HttpStatus.UNAUTHORIZED.value());
        ObjectMapper mapper = new ObjectMapper();
        String json =  mapper.writeValueAsString(resp);

        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        httpResponse.getWriter().write(json);

        //httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required headers not specified in the request");



        /*HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return;*/
    }

    @Override
    public void destroy() {
        System.out.println("RequestFilter destroy");
    }
}
