package customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {CustomCompletionException.class})
    public ResponseEntity<Object> handleCompletionException(CustomCompletionException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomException customException = new CustomException(
                ex.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException, status);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                ex.getMessage(),
                status,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException, status);
    }
}
