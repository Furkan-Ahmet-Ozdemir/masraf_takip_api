package com.example.masraf_takip_api.exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String bookNotFoundException(UserNotFoundException exception) {
        return "[" + exception.getMessage() + "] adında bir User bulunamadı.";
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public String authorNotFoundException(TransactionNotFoundException exception) {
        return "[" + exception.getMessage() + "] adında bir Transaction bulunamadı.";
    }
}
