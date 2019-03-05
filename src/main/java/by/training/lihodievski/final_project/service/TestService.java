package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.impl.competition_rate.CompetitionRateImpl;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.sql.SQLException;

public class TestService {
    protected final ConnectionPool connectionPool = ConnectionPool.getInstance ();
    public void test() throws ServiceException {
        CompetitionRateImpl competitionRate = CompetitionRateImpl.getInstance ();
        try (ProxyConnection connection = connectionPool.takeConnection ();){
            connection.setAutoCommit (false);
            competitionRate.setProxyConnection (connection);
            competitionRate.test ("","");
            competitionRate.setProxyConnection (connection);
            competitionRate.test2 ("","");
            connection.commit ();
            connection.setAutoCommit (true);
        } catch (ConnectionPoolException e) {
            throw new ServiceException (e);
        } catch (DaoException e) {
            throw  new ServiceException (e);
        } catch (SQLException e) {
            throw new ServiceException (e);
        }
    }
}
