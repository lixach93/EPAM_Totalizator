package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LeagueServiceImpl implements LeagueService {

    private static final Logger LOGGER = LogManager.getLogger (LeagueServiceImpl.class);
    private static final LeagueServiceImpl INSTANCE = new LeagueServiceImpl ();
    private DaoFactory daoFactory = DaoFactory.getInstance ();
    private LeagueDaoAbstract leagueDao = daoFactory.getLeagueDao ();
    private CategoryDaoAbstract categoryDao = daoFactory.getCategoryDao ();

    private LeagueServiceImpl() {
    }

    public static LeagueServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean createLeague(String categoryName, String leagueName) throws ServiceException {
        if(!Validator.isCategory (categoryName) || !Validator.isLeague (leagueName)){
            return false;
        }
        Category category = Category.valueOf (categoryName.toUpperCase ());
        League league = new League (leagueName, category);
        try {
            leagueDao.insert (league);
            return true;
        } catch (DaoException e) {
            LOGGER.error ("Exception in createLeague in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }

    }
    @Override
    public List<League> getLeagueByCategory(long categoryId) throws ServiceException {
        try {
            Category category = categoryDao.getCategoryById(categoryId);
            return leagueDao.getLeaguesByCategory (category);
        } catch (DaoException e) {
            LOGGER.error ("Exception in getLeagueByCategory in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<League> getLeagues() throws ServiceException {
        try {
            return leagueDao.getAll ();
        } catch (DaoException e) {
            LOGGER.error ("Exception in getLeagues in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean deleteLeague(String leagueIdStr) throws ServiceException {
        if(!Validator.isId (leagueIdStr)){
            return false;
        }
        long leagueId = Long.parseLong (leagueIdStr);
        League league = new League (leagueId);
        try {
            leagueDao.delete (league);
        } catch (DaoException e) {
            LOGGER.error ("Exception in  deleteLeague in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }
        return true;
    }
}
