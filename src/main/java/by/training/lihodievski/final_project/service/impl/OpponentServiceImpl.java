package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Opponent;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.opponent.OpponentDaoAbstract;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public class OpponentServiceImpl implements OpponentService {

    private final static OpponentServiceImpl INSTANCE = new OpponentServiceImpl ();
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private final OpponentDaoAbstract opponentDao = daoFactory.getOpponentDao();
    private OpponentServiceImpl() {
    }

    public static OpponentService getInstance() {
        return INSTANCE;
    }

    @Override
    public void addOpponent(long leagueId, String teamName) throws ServiceException {
        Opponent opponent = new Opponent ();
        opponent.setId (leagueId);
        opponent.setNameOpponent (teamName);
        try {
            opponentDao.insert (opponent);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }

    }

    @Override
    public List<Opponent> getOpponentByLeague(long id) throws ServiceException {
        League league = new League ();
        league.setId (id);
        try {
            return opponentDao.getOpponentByLeague(league);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }
}
