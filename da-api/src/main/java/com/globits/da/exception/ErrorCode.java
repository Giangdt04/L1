package com.globits.da.exception;

import org.springframework.http.HttpStatus;


public enum ErrorCode{
    CODE_NOT_EXISTED(1001,"Code not existed",HttpStatus.NOT_FOUND),

    REQUEST_NOT_NULL(1002,"Request cannot be null",HttpStatus.BAD_REQUEST),

    NO_EMPLOYEES_FOUND(1003,"No employees found", HttpStatus.NOT_FOUND),

    CODE_ALREADY_EXISTS(1004, "Code was exists", HttpStatus.BAD_REQUEST),

    COMMUNE_NOT_EXISTS(1005, " Commune does not belong to District", HttpStatus.BAD_REQUEST),

    DISTRICT_NOT_EXISTS(1006, " District does not belong to Province", HttpStatus.BAD_REQUEST),

    EMPLOYEE_NOT_FOUND(1007, " Employee not found", HttpStatus.NOT_FOUND),

    CERTIFICATE_EXISTS(1008, "Certificate already exists for this employee from this province", HttpStatus.BAD_REQUEST),

    CERTIFICATE_LIMIT_REACHED(1009,"Employee cannot have more than 3 active certificates of the same type" , HttpStatus.BAD_REQUEST),

    PROVINCE_NOT_FOUND(1011, " Province not found", HttpStatus.NOT_FOUND),

    DISTRICT_NOT_FOUND(1012, " District not found", HttpStatus.NOT_FOUND),

    COMMUNE_NOT_FOUND(1013, " Commune not found", HttpStatus.NOT_FOUND),

    BEGIN_NOT_NULL(1014, "Begin date is required.", HttpStatus.BAD_REQUEST),
    ;
    private int code;

    private String message;

    private HttpStatus status;

    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
