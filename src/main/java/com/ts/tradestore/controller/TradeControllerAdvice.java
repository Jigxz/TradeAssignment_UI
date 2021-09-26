package com.ts.tradestore.controller;

import com.ts.tradestore.exception.ErrorMessage;
import com.ts.tradestore.exception.InvalidTradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Optional;

@ControllerAdvice
public class TradeControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTradeException.class)
    public ResponseEntity<ErrorMessage> notFoundException(final InvalidTradeException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE, e.getId());
    }

    private ResponseEntity<ErrorMessage> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), LocalDate.now(), logRef, message), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
    }
}
