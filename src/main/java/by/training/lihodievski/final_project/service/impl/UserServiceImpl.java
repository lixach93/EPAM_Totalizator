package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.BCryptUtil;
import by.training.lihodievski.final_project.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;


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
        password = BCryptUtil.hashString (password);
        User user = new User (login, email, password);
        try {
            userDaoAbstract.insert (user);
        } catch (DaoException e) {
            if(((SQLException) e.getCause ()).getSQLState ().equals ("23000")) {
                String error = e.getMessage ();
                if (error.contains (PARAMETER_LOGIN)) {
                    throw new UserException (ERROR_LOGIN_USED);
                }
                if (error.contains (PARAMETER_EMAIL)) {
                    throw new UserException (ERROR_EMAIL_USED);
                }
            }
            LOGGER.error ("Exception  in registration in UserServiceImpl.class", e);
            throw new ServiceException ("Exception register user",e);
        }
    }

    @Override
    public User login(String login, String password) throws ServiceException, UserException {
        checkLogin (login);
        checkPassword (password);
        User user = null;
        try {
            user = userDaoAbstract.getUserByLogin (login);
            if(user != null && BCryptUtil.isValidHash (password, user.getPassword ())){
                return user;
            }else{
                throw new UserException(ERROR_INPUT_USER_IS_NOT_EXISTS);
                }
        } catch (DaoException e) {
            LOGGER.error ("Exception  in login in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public User getUserById(long id) throws ServiceException {
        User user = new User ();
        user.setId (id);
        try {
            return userDaoAbstract.getUserById (user);
        } catch (DaoException e) {
            LOGGER.error ("Exception  in getUserById in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean updateBalance(long id, String cardNumberStr, String moneyStr) throws ServiceException {
        if(!Validation.isCardNumber (cardNumberStr) || !Validation.isMoney (moneyStr)){
            return false;
        }
        User user = new User ();
        user.setId (id);
        try {
            user = userDaoAbstract.getUserById (user);
            double money = Double.parseDouble (moneyStr);
            user.setMoney (user.getMoney () + money);
            userDaoAbstract.update (user);
        } catch (DaoException e) {
            LOGGER.error ("Exception  in updateBalance in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
        return true;
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            return userDaoAbstract.getAll ();
        } catch (DaoException e) {
            LOGGER.error ("Exception in getUsers in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean changeRole(String role, String idStr) throws ServiceException {
        if(!Validation.isRole(role)|| !Validation.isId (idStr)){
            return false;
        }
        long userId = Long.parseLong (idStr);
        User user = new User (userId);
        try {
            user = userDaoAbstract.getUserById (user);
            user.setRoleType (RoleType.valueOf (role.toUpperCase ()));
            userDaoAbstract.update (user);
            return true;
        } catch (DaoException e) {
            LOGGER.error ("Exception in changeRole in UserServiceImpl.class", e);
            throw new ServiceException (e);
        }
    }

    private void checkPassword(String password, String confirmPassword) throws UserException {
        if(!password.equals (confirmPassword)){
            throw new UserException (ERROR_INPUT_PASSWORD_IS_NOT_EQUALS);
        }
        if(!password.matches ("^[A-Za-zА-я0-9]{6,}$")){
            throw new UserException (ERROR_INPUT_PASSWORD_NOT_VALID);
        }
    }
    private void checkPassword(String password) throws UserException {
        if(!password.matches ("^[A-Za-zА-я0-9]{6,}$")){
            throw new UserException (ERROR_INPUT_PASSWORD_NOT_VALID);
        }
    }
    private void checkLogin(String login) throws UserException {
        if(!login.matches ("\\w{4,20}")){
            throw new UserException (ERROR_INPUT_LOGIN_NOT_VALID);
        }
    }

}
