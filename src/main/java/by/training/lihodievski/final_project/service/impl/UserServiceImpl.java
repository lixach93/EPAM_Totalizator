package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {

    private final static UserServiceImpl INSTANCE = new UserServiceImpl ();
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
            if(((SQLException) e.getCause ()).getSQLState ().equals ("23000")){
                String error = e.getMessage ();
                if(error.contains ("login")){
                    User exception = new User ();
                    exception.setLogin (login);
                    throw new UserException ("this login is used", exception);}
                if(error.contains ("email")){
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

    private void checkPassword(String password, String confirmPassword) throws UserException {
        if(!password.equals (confirmPassword)){
            throw new UserException ("Password is not equals");
        }
        if(!password.matches ("\\d{3,5}[a-z]{1}[A-Z]{1}")){
            throw new UserException ("Password is not valid");
        }
    }
    private void checkPassword(String password) throws UserException {
        if(!password.matches ("\\d{3,5}[a-z]{1}[A-Z]{1}")){
            throw new UserException ("Password is not valid");
        }
    }

    private void checkLogin(String login) throws UserException {
        if(!login.matches ("\\w+")){
            User exception = new User ();
            exception.setLogin (login);
            throw new UserException ("Not valid login",exception);
        }
    }

}
