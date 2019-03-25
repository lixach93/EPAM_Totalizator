package by.training.lihodievski.final_project.dao.impl.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class UserDaoImpl extends UserDaoAbstract  {

    private static final Logger LOGGER = LogManager.getLogger (UserDaoImpl.class);
    private static final UserDaoImpl INSTANCE = new UserDaoImpl ();

    private UserDaoImpl() {
    }

    public static UserDaoAbstract getInstance() {
        return INSTANCE;
    }

    @Override
    protected void preparedStatementInsert(PreparedStatement preparedStatement, User object) throws DaoException {
        try {
            preparedStatement.setString (1, object.getLogin ());
            preparedStatement.setString (2, object.getEmail ());
            preparedStatement.setString (3, object.getPassword ());
        }catch (SQLException e){
            LOGGER.error ("Exception in preparedStatementInsert UserDaoImpl ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, User object) throws DaoException {
        try {
            preparedStatement.setDouble (1, object.getMoney ());
            preparedStatement.setString (2, object.getRoleType ().getValue ());
            preparedStatement.setLong (3, object.getId ());
        }catch (SQLException e){
            LOGGER.error ("Exception in preparedStatementUpdate UserDaoImpl ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet, List<User> list) throws DaoException {
        try{
            while (resultSet.next ()){
                User user = new User ();
                user.setId (resultSet.getLong (USER_ID));
                user.setLogin (resultSet.getString (USER_LOGIN));
                user.setEmail (resultSet.getString (USER_EMAIL));
                user.setPassword (resultSet.getString (USER_PASSWORD));;
                user.setMoney (resultSet.getDouble (USER_MONEY ));
                user.setRoleType (RoleType.valueOf (resultSet.getString (USER_ROLE).toUpperCase ()));
                list.add (user);
            }
        }catch (SQLException e){
            LOGGER.error ("Exception in parseResultSet in UserDaoImpl ", e);
            throw new DaoException (e);
        }
        return list;
    }

    public User getUserByLogin(String login) throws DaoException {
        String query = getUserByLoginQuery ();
        List<User> list = new ArrayList<> ();
        try (ProxyConnection connection= connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,login);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getUserByLogin UserDaoImpl ", e);
            throw new DaoException (e);
        }
        if(list.size () == 0){
            return null;
        }else {
            return list.iterator ().next ();
        }
    }

    @Override
    public User getUserById(User user) throws DaoException {
        String query = getUserByIdQuery ();
        List<User> list = new ArrayList<> ();
        try (ProxyConnection connection= connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setLong (1,user.getId ());
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getUserById in UserDaoImpl ", e);
            throw new DaoException (e);
        }
        return list.iterator ().next ();
    }

    @Override
    public List<User> getLimitUsers(int numberPage) throws DaoException {
        String query = getSelectSql ();
        List<User> users = new ArrayList<> ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            preparedStatement.setInt (1, numberPage);
            preparedStatement.setInt (2, PER_PAGE);
            ResultSet resultSet = preparedStatement.executeQuery ();
            users = parseResultSet (resultSet, users);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getLimitUsers in UserDaoImpl.class ", e);
            throw new DaoException (e);
        }
        return users;
    }

    @Override
    public int getCountUsers() throws DaoException {
        String query = getCountUsersQuery ();
        try (ProxyConnection connection = connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query);){
            ResultSet resultSet = preparedStatement.executeQuery ();
            resultSet.next ();
            return  resultSet.getInt (USER_ID_ALIAS);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in getCountActiveBetForUser in BetDaoImpl.class ", e);
            throw new DaoException (e);
        }
    }
}
