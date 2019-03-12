package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.user.UserDaoImpl;
import by.training.lihodievski.final_project.service.BettingService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.util.Constants;
import by.training.lihodievski.final_project.util.Validation;
import by.training.lihodievski.final_project.util.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BettingServiceImpl implements BettingService {

    private static final Logger LOGGER = LogManager.getLogger (UserDaoImpl.class);
    private static BettingServiceImpl INSTANCE = new BettingServiceImpl ();
    private DaoFactory factory = DaoFactory.getInstance ();
    private UserDaoAbstract userDao = factory.getUserDao ();
    private BettingDaoAbstract bettingDao = factory.getBettingDao ();
    private BettingServiceImpl() {
    }
    public static BettingServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean makeRate(long userId, String eventIdStr, String opponentStr, String moneyStr) throws ServiceException, ValidationException, UserException {
        if(!Validation.isPositiveNumber (eventIdStr) || !Validation.isPositiveNumber (opponentStr)
                || !Validation.isMoney (moneyStr)){
            LOGGER.error ("Error in validation in UserDaoImpl class ");
            throw new ValidationException (Constants.ERROR_MESSAGE);
        }
        User user = new User (userId);
        try{
            user = userDao.getUserById(user);
            double money = Double.parseDouble (moneyStr);
            user.setMoney (user.getMoney () - money);
            if(user.getMoney () < 0){
                LOGGER.error ("Error , not correct value in  UserDaoImpl class ");
                throw new UserException (Constants.ERROR_NO_MONEY);
            }
            long eventId = Long.parseLong (eventIdStr);
            Event event = new Event (eventId);
            int opponent = Integer.parseInt (opponentStr);
            Betting betting = new Betting (user, event,opponent,money);
            return bettingDao.insertBetting (betting);
        }catch (DaoException e) {
            LOGGER.error ("Error in UserDaoImpl class ", e);
            throw  new ServiceException (e);
        }
    }

    @Override
    public boolean makeRate(long userId, String eventIdStr, String firstScoreStr, String secondScoreStr, String moneyStr) throws ServiceException, ValidationException, UserException {
        if(!Validation.isPositiveNumber (eventIdStr) || !Validation.isPositiveNumber (firstScoreStr)
                || !Validation.isMoney (moneyStr) || !Validation.isPositiveNumber (secondScoreStr)){
            LOGGER.error ("Error in validation in UserDaoImpl class ");
            throw new ValidationException (Constants.ERROR_MESSAGE);
        }
        User user = new User (userId);
        try{
            user = userDao.getUserById(user);//ЕСЛІ НЕ БУДЕТ ЮЗЕРА
            double money = Double.parseDouble (moneyStr);
            user.setMoney (user.getMoney () - money);
            if(user.getMoney () < 0){
                LOGGER.error ("Error , not correct value in  UserDaoImpl class ");
                throw new UserException (Constants.ERROR_NO_MONEY);
            }
            long eventId = Long.parseLong (eventIdStr);
            Event event = new Event (eventId);
            int firstScore = Integer.parseInt (firstScoreStr);
            int secondScore = Integer.parseInt (secondScoreStr);
            Betting betting = new Betting (user, event, firstScore, secondScore, money);
            return bettingDao.insertBetting (betting);
        }catch (DaoException e) {
            LOGGER.error ("Error in UserDaoImpl class ", e);
            throw  new ServiceException (e);
        }
    }

    @Override
    public List<Betting> getActiveBettingForUser(long userId) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try{
            user = userDao.getUserById(user);
            return bettingDao.getActiveBettingForUser(user);
        } catch (DaoException e) {
            LOGGER.error ("Error in getActiveBettingForUser in UserDaoImpl.class " ,e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<Betting> getResultBettingForUser(long userId) throws ServiceException {
        User user = new User ();
        user.setId (userId);
        try{
            user = userDao.getUserById(user);
            return bettingDao.getResultBettingForUser(user);
        } catch (DaoException e) {
            LOGGER.error ("Error in getResultBettingForUser in UserDaoImpl.class " ,e);
            throw new ServiceException (e);
        }
    }
}
