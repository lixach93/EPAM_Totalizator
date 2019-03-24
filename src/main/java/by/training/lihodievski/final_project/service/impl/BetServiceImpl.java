package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Bet;
import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.bet.BetDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.service.BetService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.util.Constants;
import by.training.lihodievski.final_project.util.PageUtil;
import by.training.lihodievski.final_project.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BetServiceImpl implements BetService {

    private static final Logger LOGGER = LogManager.getLogger (BetServiceImpl.class);
    private static BetServiceImpl INSTANCE = new BetServiceImpl ();
    private DaoFactory factory = DaoFactory.getInstance ();
    private UserDaoAbstract userDao = factory.getUserDao ();
    private BetDaoAbstract bettingDao = factory.getBetDao ();

    private BetServiceImpl() {
    }

    public static BetServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean makeBet(long userId, String eventIdStr, String opponentStr, String moneyStr) throws ServiceException, UserException {
        if (!Validator.isPositiveNumber (eventIdStr) || !Validator.isPositiveNumber (opponentStr)
                || !Validator.isMoney (moneyStr)) {
            return false;
        }
        User user = new User (userId);
        try {
            user = userDao.getUserById (user);
            double money = Double.parseDouble (moneyStr);
            user.setMoney (user.getMoney () - money);
            if (user.getMoney () < 0) {
                LOGGER.error ("Exception , not correct value BetServiceImpl.class");
                throw new UserException (Constants.ERROR_NO_MONEY);
            }
            long eventId = Long.parseLong (eventIdStr);
            Event event = new Event (eventId);
            int opponent = Integer.parseInt (opponentStr);
            Bet betting = new Bet (user, event, opponent, money);
            return bettingDao.insertBet (betting);
        } catch (DaoException e) {
            LOGGER.error ("Exception in makeBet in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean makeBet(long userId, String eventIdStr, String firstScoreStr, String secondScoreStr, String moneyStr) throws ServiceException, UserException {
        if (!Validator.isId (eventIdStr) || !Validator.isPositiveNumber (firstScoreStr)
                || !Validator.isMoney (moneyStr) || !Validator.isPositiveNumber (secondScoreStr)) {
            return false;
        }
        User user = new User (userId);
        try {
            user = userDao.getUserById (user);
            double money = Double.parseDouble (moneyStr);
            user.setMoney (user.getMoney () - money);
            if (user.getMoney () < 0) {
                LOGGER.error ("Exception , not correct value BetServiceImpl.class");
                throw new UserException (Constants.ERROR_NO_MONEY);
            }
            long eventId = Long.parseLong (eventIdStr);
            Event event = new Event (eventId);
            int firstScore = Integer.parseInt (firstScoreStr);
            int secondScore = Integer.parseInt (secondScoreStr);
            Bet bet = new Bet (user, event, firstScore, secondScore, money);
            return bettingDao.insertBet (bet);
        } catch (DaoException e) {
            LOGGER.error ("Exception in makeBet in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public int getCountPage(long userId) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try {
            user = userDao.getUserById (user);
            int countBet = bettingDao.getCountActiveBetForUser (user);
            return PageUtil.getCountPage (countBet);
        } catch (DaoException e) {
            LOGGER.error ("Exception in getCountUnPaymentEvents in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<Bet> getResultForUser(long userId, int page) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try {
            user = userDao.getUserById (user);
            return bettingDao.getResultForUser (user, page);
        } catch (DaoException e) {
            LOGGER.error ("Error in getResultForUser in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<Bet> getActiveBet(long userId, int numberPage) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try {
            user = userDao.getUserById (user);
            return bettingDao.getActiveBettingLimitForUser (user, numberPage);
        } catch (DaoException e) {
            LOGGER.error ("Error in getActiveBet in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public int getCountResultForUser(long id) throws ServiceException {
        User user = new User ();
        user.setId (id);
        try {
            user = userDao.getUserById (user);
            int countBet = bettingDao.getCountResultForUser (user);
            return PageUtil.getCountPage (countBet);
        } catch (DaoException e) {
            LOGGER.error ("Error in getCountResultForUser in BetServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }
}
