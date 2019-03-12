package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition_rate.EventDaoAbstract;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.Validation;
import by.training.lihodievski.final_project.util.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.bean.Rate.TEAM;

public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LogManager.getLogger (EventServiceImpl.class);
    private static final  EventServiceImpl INSTANCE = new EventServiceImpl ();
    private DaoFactory factory = DaoFactory.getInstance ();
    private EventDaoAbstract eventDao = factory.getEventDao ();
    private BettingDaoAbstract bettingDao = factory.getBettingDao ();

    private EventServiceImpl() {
    }

    public static EventService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Event> getEventsByRate(String category, String rate) throws ServiceException {
        try{
            return eventDao.getEventsByRate (category,rate);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public Event getRateById(long id) throws ServiceException {
        try {
            return eventDao.getEventById (id);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public void createCompetitionRate(long opponentFirstId, long opponentSecondId, String  typeRate) throws ServiceException {
        Rate[] rate;
        if(typeRate.equals ("all")){
            rate = new Rate[]{TEAM, Rate.TOTAL};
        }else{
            Rate currentRate = Rate.valueOf (typeRate.toUpperCase ());
            rate = new Rate[]{currentRate};
        }
        try {
            eventDao.createCompetitionRate (opponentFirstId, opponentSecondId ,rate);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }

    }

    @Override
    public List<Event> getAllUnPaymentRate() throws ServiceException {
        try {
            return eventDao.getUnPaymentRate();
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean payments(long competitionRateId) throws ServiceException {
        try {
            Event event = eventDao.getEventById (competitionRateId);
            if(event.isPayment ()){
                return false;
            }else {
                Rate rate = event.getRate ();
                Competition competition = event.getCompetition ();
                Double money = bettingDao.getMoneyForCompRate (event);
                List<Betting> bettingList = bettingDao.getAllBettingByCompRate (event);
                List<Betting> winner = new ArrayList<> ();
                for (Betting currentBetting : bettingList) {
                    checkWin (winner, currentBetting, competition, rate);
                }
                double winMoney = money / winner.size ();

                event.setPayment (true);
                bettingDao.setWinner (winner, winMoney, event);
                return true;
            }
        }catch (DaoException e){
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<Event> getAllActiveEvent() throws ServiceException {
        try {
            return eventDao.getActiveEvent ();
        } catch (DaoException e) {
            LOGGER.error ("Exception in getAllActiveEvent in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean addPercent(String eventIdStr, String percentStr) throws ServiceException, ValidationException {
        if(!Validation.isPositiveNumber (eventIdStr) || !Validation.isPositiveNumber (percentStr)){
            throw new ValidationException ();
        }
        try {
            long eventId = Long.parseLong (eventIdStr);
            Event event = eventDao.getEventById (eventId);
            if(event.getPercent () > 0){
                return false;
            }else {
                double percent = Double.parseDouble (percentStr);
                event.setPercent (percent);
                eventDao.update (event);
                return true;
            }
        }catch (DaoException e){
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    private void checkWin(List<Betting> winner, Betting currentBetting, Competition competition, Rate rate) {

        switch (rate){
            case TEAM:
               if( currentBetting.getWinner () == competition.getWinner ()){
                   winner.add (currentBetting);
               }
               break;
            case TOTAL:
                if(currentBetting.getOpponentFirstScore () == competition.getFirstOpponentResult ()
                    && currentBetting.getOpponentSecondScore () == competition.getSecondOpponentResult ()){
                    winner.add (currentBetting);
                }
                break;
        }
    }
}
