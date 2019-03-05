package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.dao.impl.competition.CompetitionDaoAbstract;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {

    private final static CompetitionServiceImpl INSTANCE = new CompetitionServiceImpl ();
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private final CompetitionDaoAbstract competitionDao = daoFactory.getCompetitionDao ();

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
    public boolean closeCompetition(int id) throws ServiceException {
        Competition competition = new Competition ();

        competition.setId (id);
        try {
           return competitionDao.updateStatus (competition);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }
}
