package com.globits.da.exception;

import com.globits.da.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice sẽ bao quanh tất cả Exception và đưa ra cái đã thiết lập
@ControllerAdvice
public class GlobalExceptionHandler {

    //ngoại lê đã xử lý
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse response = new ApiResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(response);
    }



}
