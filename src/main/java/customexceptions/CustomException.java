package customexceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomException {
    private String message;
    private HttpStatus status;
    private ZonedDateTime time;

    public CustomException(String message, HttpStatus status, ZonedDateTime time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}
