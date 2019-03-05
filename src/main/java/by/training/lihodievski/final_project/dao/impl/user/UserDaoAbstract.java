package by.training.lihodievski.final_project.dao.impl.user;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

public abstract class UserDaoAbstract extends AbstractGenericDao<User> {

    @Override
    protected String getDeleteSQL() {
        return null;
    }

    @Override
    protected String getUpdateSQL() {
        return null;
    }

    @Override
    protected String getSelectSql() {
        return null;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO totalizator.user (login,email,password) value(?, ?, ?)";
    }

    protected String getSelectSqlByLoginAndPassword() {
        return "SELECT user_id,login,email,money, role FROM totalizator.user where login = ? and password = ?";
    }

    protected String getSelectSqlById() {
        return "SELECT user_id,login,email,money, role FROM totalizator.user  where user_id = ?";
    }

    public abstract User getUserByLoginAndPassword(String login ,String password) throws DaoException;

    public abstract User getUserById(User user) throws DaoException;
}
