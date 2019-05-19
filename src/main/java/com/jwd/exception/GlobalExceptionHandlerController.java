package com.jwd.exception;

import com.jwd.model.ResponseMsg;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    /*@Bean
    public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
                final Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }*/

    //@ExceptionHandler({CustomException.class})
    //public void handleNotFoundException(HttpServletResponse res, CustomException ex) throws IOException {
    //    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
    //}

    @ExceptionHandler({CustomException.class})
    public ResponseMsg handleNotFoundException(HttpServletResponse res, CustomException ex) {
        int status = ex.getHttpStatus().value();
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), status);
        res.setStatus(status);

        return responseMsg;
    }

    @ExceptionHandler(Exception.class)
    public ResponseMsg handleException(HttpServletResponse res) throws IOException {
        ResponseMsg responseMsg = new ResponseMsg("Something went wrong", HttpStatus.BAD_REQUEST.value());
        return responseMsg;
    }
}