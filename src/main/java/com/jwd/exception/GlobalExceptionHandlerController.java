package com.jwd.exception;

import com.jwd.model.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    Logger logger = LogManager.getLogger(GlobalExceptionHandlerController.class);


    @ExceptionHandler({CustomException.class})
    public ResponseMsg handleCustomException(HttpServletResponse res, CustomException ex) {
        int status = ex.getHttpStatus().value();
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), status);
        res.setStatus(status);

        logger.error(ex.toString());

        return responseMsg;
    }

    @ExceptionHandler(Exception.class)
    public ResponseMsg handleException(HttpServletResponse res, Exception ex) {
        ResponseMsg responseMsg = new ResponseMsg("Something went wrong", HttpStatus.BAD_REQUEST.value());
        res.setStatus(HttpStatus.BAD_REQUEST.value());

        logger.error(ex.toString());

        return responseMsg;
    }
}