package by.training.lihodievski.final_project.command.exception;

public class PermissionException extends Exception {


    private static final long serialVersionUID = 5596409502746710306L;

    public PermissionException() {
    }

    public PermissionException(String message) {
        super (message);
    }

    public PermissionException(String message, Throwable cause) {
        super (message, cause);
    }

    public PermissionException(Throwable cause) {
        super (cause);
    }

    public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
