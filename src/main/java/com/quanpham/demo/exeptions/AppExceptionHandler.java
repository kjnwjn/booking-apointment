package com.quanpham.demo.exeptions;

import java.time.LocalDateTime;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.quanpham.demo.BaseRespone.response.BaseResponse;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException e) {

        BaseResponse err = new BaseResponse();
        err.setData(e);
        err.setErrorCode("500");
        err.setErrorDesc("RuntimeException");

        return ResponseEntity.ok(err);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<BaseResponse> forbidden(RuntimeException e) {

        BaseResponse err = new BaseResponse();
        err.setErrorCode("403");
        err.setErrorDesc("Forbidden");

        return ResponseEntity.ok(err);
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<BaseResponse> notFound(NotFoundException e) {

        BaseResponse err = new BaseResponse();
        err.setErrorCode("404");
        err.setErrorDesc("page not found");

        return ResponseEntity.ok(err);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<BaseResponse> notFound(MethodArgumentNotValidException e) {

        BaseResponse err = new BaseResponse();
        err.setErrorCode("400");
        err.setErrorDesc("Validation failed");

        return ResponseEntity.ok(err);
    }
}
