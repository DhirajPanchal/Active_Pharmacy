package io.active.pharmacy.gateway.exception;

import io.active.pharmacy.gateway.dto.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomErrorException.class)
    protected ResponseEntity<CustomErrorResponse> handleCustomError(RuntimeException ex) {


        CustomErrorException customErrorException = (CustomErrorException) ex;

        System.out.println("__CustomErrorException : "+customErrorException.getErrorResponse());

        return ResponseEntity.status(customErrorException.getErrorResponse().getStatus())
                .body(customErrorException.getErrorResponse());
    }





    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        System.out.println("__MethodArgumentNotValidException");
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
        System.out.println("__Exception");
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
        System.out.println("__RuntimeException");
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<Map<String, List<String>>> handleResponseStatusException(RuntimeException ex) {
        System.out.println("__ResponseStatusException");
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public final ResponseEntity<CustomErrorResponse> handleRuntimeExceptions2(WebExchangeBindException ex) {
        System.out.println("__WebExchangeBindException");

//        List<String> errors = ex.getBindingResult().getFieldErrors()
//                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
//        System.out.println(errors);

        List<String> objectErrors = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

        System.out.println(objectErrors);

        CustomErrorResponse customErrorResponse = CustomErrorResponse
                .builder()
                .traceId(UUID.randomUUID().toString())
                .timestamp(OffsetDateTime.now().now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid input(s)")
                .errors(objectErrors)
                .build();
        return ResponseEntity.status(customErrorResponse.getStatus())
                .body(customErrorResponse);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        System.out.println("__getErrorsMap");
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    @ExceptionHandler(UserNotFoundException.class)
    protected ProblemDetail handleNotFound(RuntimeException ex) {
        System.out.println("__UserNotFoundException");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("User not found");
        problemDetail.setType(URI.create("https://example.com/problems/user-not-found"));
        //problemDetail.setProperty("errors", List.of(ErrorDetails.API_USER_NOT_FOUND));
        return problemDetail;
    }


}

