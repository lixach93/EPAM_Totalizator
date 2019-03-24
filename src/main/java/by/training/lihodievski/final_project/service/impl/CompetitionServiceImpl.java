package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoAbstract;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class CompetitionServiceImpl implements CompetitionService {

    private static final Logger LOGGER = LogManager.getLogger (CompetitionServiceImpl.class);
    private final static CompetitionServiceImpl INSTANCE = new CompetitionServiceImpl ();
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private final CompetitionDaoAbstract competitionDao = daoFactory.getCompetitionDao ();
    private final CategoryDaoAbstract categoryDao = daoFactory.getCategoryDao ();

    private CompetitionServiceImpl() {
    }

    public static CompetitionService getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Competition> getCompetition() throws ServiceException {
        try {
            return competitionDao.getAll ();
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }

    @Override
    public Competition closeCompetition(String competitionIdStr) throws ServiceException {
        if(!Validator.isId (competitionIdStr)){
            return null;
        }
        long competitionId = Long.valueOf (competitionIdStr);
        Competition competition = new Competition (competitionId);
        try {
            Category category = categoryDao.getCategoryByCompetition (competition);
            randomResult(competition, category);
            return competitionDao.changeStatus (competition);
        } catch (DaoException e) {
            LOGGER.error ("Error in closeCompetition in CompetitionServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    private void randomResult(Competition competition, Category category) {
        switch (category){
            case FOOTBALL:
                findWinner(competition, 4);
                break;
            case HOCKEY:
                findWinner (competition, 6);
                break;
            case BASKETBALL:
                findWinner (competition, 111);
                break;
        }
    }

    private void findWinner(Competition competition, int score) {
        Random random = new Random ();
        competition.setFirstOpponentResult (random.nextInt (score));
        competition.setSecondOpponentResult (random.nextInt (score));
        if(competition.getFirstOpponentResult () > competition.getSecondOpponentResult ()){
            competition.setWinner (1);
        }else if(competition.getFirstOpponentResult () < competition.getSecondOpponentResult ()){
            competition.setWinner (2);
        }else{
            competition.setWinner (0);
        }
    }
}
