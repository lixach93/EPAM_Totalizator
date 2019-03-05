package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.service.BettingService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

public class BettingServiceImpl implements BettingService {
    private static BettingServiceImpl INSTANCE = new BettingServiceImpl ();
    public static BettingServiceImpl getInstance() {
        return INSTANCE;
    }
    private final DaoFactory factory = DaoFactory.getInstance ();
    private final UserDaoAbstract userDao = factory.getUserDao ();
    private final BettingDaoAbstract bettingDao = factory.getBettingDao ();
    private BettingServiceImpl() {
    }

    @Override
    public void makeRate(long userId, long competitionId,int opponent, double money) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try{
            user = userDao.getUserById(user);
        }catch (DaoException e){
            throw new ServiceException (e);
        }
        user.setMoney (user.getMoney () - money);
        CompetitionRate competitionRate = new CompetitionRate ();
        competitionRate.setId (competitionId);
        Betting betting = new Betting (user,competitionRate,opponent,money);

        try {
            bettingDao.insertBetting (betting);
        } catch (DaoException e) {
            throw  new ServiceException (e);
        }

    }
}
