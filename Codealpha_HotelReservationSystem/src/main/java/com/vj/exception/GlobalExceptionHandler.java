package com.vj.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> body(HttpStatus s, String msg) {
        return ResponseEntity.status(s).body(Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", s.value(), "error", msg));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(NotFoundException e) { return body(HttpStatus.NOT_FOUND, e.getMessage()); }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException e) { return body(HttpStatus.BAD_REQUEST, e.getMessage()); }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> conflict(IllegalStateException e) { return body(HttpStatus.CONFLICT, e.getMessage()); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> invalid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage()).reduce((a, b) -> a + "; " + b).orElse("Invalid request");
        return body(HttpStatus.BAD_REQUEST, msg);
    }
}
