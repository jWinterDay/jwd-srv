package com.jwd.exception;

import com.jwd.model.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestControllerAdvice
public class GlobalExceptionHandlerController {
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
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseMsg;
    }
}