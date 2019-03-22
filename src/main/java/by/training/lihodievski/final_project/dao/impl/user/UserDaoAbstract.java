package by.training.lihodievski.final_project.dao.impl.user;

import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.AbstractGenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;

public abstract class UserDaoAbstract extends AbstractGenericDao<User> {

    public abstract User getUserByLogin(String login) throws DaoException;
    public abstract User getUserById(User user) throws DaoException;

    @Override
    protected String getDeleteSQL() throws DaoException {
        throw new DaoException ("Operation not supported");
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE totalizator.user SET money = ?,role = ?  WHERE user_id = ?";
    }

    @Override
    protected String getSelectSql() {
        return "SELECT user_id,login,email,password,money,role FROM totalizator.user";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO totalizator.user (login,email,password) VALUE (?, ?, ?)";
    }

    String getUserByLoginQuery() {
        return "SELECT user_id,login,email,password,money, role FROM totalizator.user WHERE login = ?";
    }

    String getUserByIdQuery() {
        return "SELECT user_id,login,email,password,money,role FROM totalizator.user WHERE user_id = ?";
    }


}
