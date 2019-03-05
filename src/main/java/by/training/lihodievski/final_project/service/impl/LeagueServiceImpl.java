package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public class LeagueServiceImpl implements LeagueService {

    private static LeagueServiceImpl INSTANCE = new LeagueServiceImpl ();
    public static LeagueServiceImpl getInstance() {
        return INSTANCE;
    }
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private LeagueDaoAbstract leagueDao = daoFactory.getLeagueDao ();
    private CategoryDaoAbstract categoryDao = daoFactory.getCategoryDao ();
    private LeagueServiceImpl() {
    }

    @Override
    public void addLeague(long id, String name) throws ServiceException {
        League league = new League ();
        league.setId (id);
        league.setNameLeague (name);
        try {
            leagueDao.insert (league);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }
    @Override
    public List<League> getLeagueByCategory(long id) throws ServiceException {
        try {
            Category category = categoryDao.getCategoryById(id);
            return leagueDao.getLeaguesByCategory (category);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }
}
