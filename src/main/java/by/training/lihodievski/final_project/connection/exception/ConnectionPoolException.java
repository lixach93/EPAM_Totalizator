package by.training.lihodievski.final_project.connection.exception;

public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = -6212184677700761249L;

    public ConnectionPoolException() {
        super ();
    }

    public ConnectionPoolException(String message) {
        super (message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super (message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super (cause);
    }

    public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
