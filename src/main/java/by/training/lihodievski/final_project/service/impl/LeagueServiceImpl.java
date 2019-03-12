package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.service.LeagueService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.Constants;
import by.training.lihodievski.final_project.util.Validation;
import by.training.lihodievski.final_project.util.ValidationException;
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
    public boolean createLeague(String categoryName, String name) throws ServiceException, ValidationException {
        if(!Validation.isString (categoryName) || !Validation.isString (name)){
            LOGGER.error ("Error in validation in LeagueServiceImpl.class ");
            throw new ValidationException (Constants.ERROR_MESSAGE);
        }
        Category category = Category.valueOf (categoryName.toUpperCase ());
        League league = new League (name, category);
        try {
           leagueDao.insert (league);
        } catch (DaoException e) {
            LOGGER.error ("Error in createLeague in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }
        return true;
    }
    @Override
    public List<League> getLeagueByCategory(long categoryId) throws ServiceException {
        try {
            Category category = categoryDao.getCategoryById(categoryId);
            return leagueDao.getLeaguesByCategory (category);
        } catch (DaoException e) {
            LOGGER.error ("Error in getLeagueByCategory in LeagueServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }
}
