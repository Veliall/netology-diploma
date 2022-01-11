package com.example.netologyhibernate.excteption;

/**
 * @author Igor Khristiuk on 11.01.2022
 */
public class ExpiredJwtException extends AppException{
    public ExpiredJwtException() {
        super();
    }

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredJwtException(Throwable cause) {
        super(cause);
    }
}
