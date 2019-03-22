package by.training.lihodievski.final_project.command.exception;

public class CommandException extends Exception {


    private static final long serialVersionUID = 4637422163723844650L;

    public CommandException() {
        super ();
    }

    public CommandException(String message) {
        super (message);
    }

    public CommandException(String message, Throwable cause) {
        super (message, cause);
    }

    public CommandException(Throwable cause) {
        super (cause);
    }

    protected CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
