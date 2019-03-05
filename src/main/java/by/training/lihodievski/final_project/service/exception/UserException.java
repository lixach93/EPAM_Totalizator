package by.training.lihodievski.final_project.service.exception;

import by.training.lihodievski.final_project.bean.User;

public class UserException extends Exception {

    private User user;

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


    public UserException(String message, User exception) {
       this(message);
       this.user = exception;
    }

    public User getUser() {
        return user;
    }
}