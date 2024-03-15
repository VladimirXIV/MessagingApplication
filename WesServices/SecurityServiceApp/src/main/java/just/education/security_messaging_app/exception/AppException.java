package just.education.security_messaging_app.exception;

import java.util.Date;


public class AppException extends Exception {

    private int status;
    private String message;
    private Date timestamp;


    public AppException() {
    }

    public AppException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}