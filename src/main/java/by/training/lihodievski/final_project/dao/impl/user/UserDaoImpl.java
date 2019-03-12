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
            LOGGER.error ("Exception in UserDaoImpl ", e);
            throw new DaoException (e);
        }
    }

    @Override
    protected void preparedStatementUpdate(PreparedStatement preparedStatement, User object) throws DaoException {

    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet, List<User> list) throws DaoException {
        try{
            while (resultSet.next ()){
                User user = new User ();
                user.setId (resultSet.getLong ("user_id"));
                user.setLogin (resultSet.getString ("login"));
                user.setEmail (resultSet.getString ("email"));
                user.setMoney (resultSet.getInt ("money"));
                user.setRoleType (RoleType.valueOf (resultSet.getString ("role").toUpperCase ()));
                list.add (user);
            }
        }catch (SQLException e){
            LOGGER.error ("Exception in UserDaoImpl ", e);
            throw new DaoException (e);
        }
        return list;
    }

    public User getUserByLoginAndPassword(String login ,String password) throws DaoException {
        String query = getSelectSqlByLoginAndPassword ();
        List<User> list = new ArrayList<> ();
        try (ProxyConnection connection= connectionPool.takeConnection ();
             PreparedStatement preparedStatement = connection.prepareStatement (query)) {
            preparedStatement.setString (1,login);
            preparedStatement.setString (2,password);
            ResultSet resultSet = preparedStatement.executeQuery ();
            list = parseResultSet(resultSet, list);
        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.error ("Exception in UserDaoImpl ", e);
            throw new DaoException (e);
        }
        return list.iterator ().next ();
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
            LOGGER.error ("Exception in UserDaoImpl ", e);
            throw new DaoException (e);
        }
        return list.iterator ().next ();
    }
}
