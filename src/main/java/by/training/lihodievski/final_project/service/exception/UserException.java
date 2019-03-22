package by.training.lihodievski.final_project.service.exception;



public class UserException extends Exception {

    private static final long serialVersionUID = -6556436295054472854L;

    public UserException() {
    }

    public UserException(String message) {
        super (message);
    }
    public UserException(String message, Throwable cause) {
        super (message, cause);
    }

    public UserException(Throwable cause) {
        super (cause);
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
