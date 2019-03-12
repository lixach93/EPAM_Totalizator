package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.command.user.ShowUserPageCommand;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static by.training.lihodievski.final_project.util.Constants.ERROR_INPUT_LOGIN_NOT_VALID;
import static by.training.lihodievski.final_project.util.Constants.ERROR_INPUT_PASSWORD_IS_NOT_EQUALS;
import static by.training.lihodievski.final_project.util.Constants.ERROR_INPUT_PASSWORD_NOT_VALID;


public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger (UserServiceImpl.class);
    private static final  UserServiceImpl INSTANCE = new UserServiceImpl ();
    private DaoFactory daoFactory = DaoFactory.getInstance ();
    private UserDaoAbstract userDaoAbstract = daoFactory.getUserDao ();
    private UserServiceImpl() {
    }
    public static UserServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public void registration(String login, String email, String password, String confirmPassword) throws ServiceException,UserException {
        checkLogin (login);
        checkPassword(password,confirmPassword);
        User user = new User (login, email, password);
        try {
            userDaoAbstract.insert (user);
        } catch (DaoException e) {
            if(((SQLException) e.getCause ()).getSQLState ().equals ("23000")) {
                String error = e.getMessage ();
                if (error.contains ("login")) {
                    User exception = new User ();
                    exception.setLogin (login);
                    throw new UserException ("this login is used", exception);
                }
                if (error.contains ("email")) {
                    User exception = new User ();
                    exception.setEmail (email);
                    throw new UserException ("this email is used", exception);
                }
            }
            throw new ServiceException ("Exception register user",e);
        }
    }

    @Override
    public User login(String login, String password) throws ServiceException, UserException {
        checkLogin (login);
        checkPassword (password);
        User user = null;
        try {
            user = userDaoAbstract.getUserByLoginAndPassword (login, password);
        } catch (DaoException e) {
             throw new ServiceException (e);
        }
        return user;
    }

    @Override
    public User getUserById(long id) throws ServiceException {
        User user = new User ();
        user.setId (id);
        try {
            return userDaoAbstract.getUserById (user);
        } catch (DaoException e) {
            LOGGER.error ("Error in getUserById in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
    }

    private void checkPassword(String password, String confirmPassword) throws UserException {
        if(!password.equals (confirmPassword)){
            throw new UserException (ERROR_INPUT_PASSWORD_IS_NOT_EQUALS);
        }
        if(!password.matches ("\\d{3,5}[a-z]{1}[A-Z]{1}")){
            throw new UserException (ERROR_INPUT_PASSWORD_NOT_VALID);
        }
    }
    private void checkPassword(String password) throws UserException {
        if(!password.matches ("\\d{3,5}[a-z]{1}[A-Z]{1}")){
            throw new UserException (ERROR_INPUT_PASSWORD_NOT_VALID);
        }
    }

    private void checkLogin(String login) throws UserException {
        if(!login.matches ("\\w{4,10}")){
            User exception = new User ();
            exception.setLogin (login);
            throw new UserException (ERROR_INPUT_LOGIN_NOT_VALID, exception);
        }
    }

}
