package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.betting.BetDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition_rate.EventDaoAbstract;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.PageUtil;
import by.training.lihodievski.final_project.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.bean.Rate.TEAM;
import static by.training.lihodievski.final_project.bean.Rate.TOTAL;

public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LogManager.getLogger (EventServiceImpl.class);
    private static final  EventServiceImpl INSTANCE = new EventServiceImpl ();
    private DaoFactory factory = DaoFactory.getInstance ();
    private EventDaoAbstract eventDao = factory.getEventDao ();
    private BetDaoAbstract betDao = factory.getBetDao ();

    private EventServiceImpl() {
    }

    public static EventService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Event> getEventsByRate(Rate rate, int numberPage) throws ServiceException {
        try{
            return eventDao.getEventsByRate (rate, numberPage);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public int getCountPage(String categoryName) throws ServiceException {
        if(!Validation.isCategory(categoryName)){
            return 0;
        }
        try{
            Category category = Category.valueOf (categoryName.toUpperCase ());
            int countEvent =  eventDao.getCountEventByCategory (category);
            return PageUtil.getCountPage (countEvent);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public int getCountPageUnPaymentEvents() throws ServiceException {
        try{
            int countEvent =  eventDao.getCountPageUnPaymentEvents ();
            return PageUtil.getCountPage (countEvent);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public int getCountPage(Rate rate) throws ServiceException {
        try{
            int countEvent =  eventDao.getCountEventByRate (rate);
            return PageUtil.getCountPage (countEvent);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }



    @Override
    public boolean createEvent(String teamFirstIdStr, String teamSecondIdStr, String  typeRate) throws ServiceException {
        if(!Validation.isId (teamFirstIdStr) || !Validation.isId (teamSecondIdStr) || !Validation.isRate (typeRate)){
                return false;
            }
        try{
            long teamFirstId = Long.parseLong (teamFirstIdStr);
            long teamSecondId = Long.parseLong (teamSecondIdStr);
            if(teamFirstId == teamSecondId){
                return false;
            }
            Rate[] rate;
            if(typeRate.equals ("all")){
                rate = new Rate[]{TEAM, TOTAL};
            }else{
                Rate currentRate = Rate.valueOf (typeRate.toUpperCase ());
                rate = new Rate[]{currentRate};
            }
            eventDao.createEvent (teamFirstId, teamSecondId, rate);
        } catch (DaoException e) {
            LOGGER.error ("Exception in createEvent in EventServiceImpl", e);
            throw new ServiceException (e);
        }
        return true;
    }

    @Override
    public List<Event> getUnPaymentEvents(int page) throws ServiceException {
        try {
            return eventDao.getUnPaymentEvents (page);
        } catch (DaoException e) {
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean payments(String eventIdStr) throws ServiceException {
        if(!Validation.isId (eventIdStr)){
            return false;
        }
        try {
            long eventId = Long.parseLong (eventIdStr);
            Event event = eventDao.getEventById (eventId);
            Rate rate = event.getRate ();
            Competition competition = event.getCompetition ();
            Double betMoney = betDao.getBetMoneyByEvent (event);
            List<Bet> bets = betDao.getBetsByEvent (event);
            List<Bet> winner = new ArrayList<> ();
                for (Bet currentBet : bets) {
                    checkWin (winner, currentBet, competition, rate);
                }
            double usersWinMoney;
            if(winner.size () == 0){
                usersWinMoney = 0;
            }else {
                if (event.getPercent () != 0) {
                    usersWinMoney = (betMoney - (betMoney * (event.getPercent () / 100))) / winner.size ();
                }else{
                    usersWinMoney = betMoney / winner.size ();
                }
            }
            double totalizatorWinMoney = betMoney - (usersWinMoney * winner.size ());
            event.setPayment (true);
            event.setWinPercent (totalizatorWinMoney);
            if(winner.size () == 0){
                return eventDao.closeEvent (event);
            }else {
                return betDao.setWinner (winner, usersWinMoney, event);
            }
        }catch (DaoException e){
            LOGGER.error ("Exception in payments in EventServiceImpl", e);
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
    public boolean addPercent(String eventIdStr, String percentStr) throws ServiceException {
        if(!Validation.isId (eventIdStr) || !Validation.isPercent (percentStr)){
            return false;
        }
        try {
            long eventId = Long.parseLong (eventIdStr);
            Event event = eventDao.getEventById (eventId);
            double percent = Double.parseDouble (percentStr);
            event.setPercent (percent);
            return eventDao.updatePercent (event);
        }catch (DaoException e){
            LOGGER.error ("Exception in EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    @Override
    public List<Event> getEventsByCategory(String categoryParameter, int numberPage) throws ServiceException {
        try {
            Category category = Category.valueOf (categoryParameter.toUpperCase ());
            return eventDao.getEventsByCategory (category, numberPage);
        } catch (DaoException e) {
            LOGGER.error ("Exception in getEventsByCategory in  EventServiceImpl", e);
            throw new ServiceException (e);
        }
    }

    private void checkWin(List<Bet> winner, Bet currentBet, Competition competition, Rate rate) {

        switch (rate){
            case TEAM:
               if( currentBet.getWinner () == competition.getWinner ()){
                   winner.add (currentBet);
               }
               break;
            case TOTAL:
                if(currentBet.getOpponentFirstScore () == competition.getFirstOpponentResult ()
                    && currentBet.getOpponentSecondScore () == competition.getSecondOpponentResult ()){
                    winner.add (currentBet);
                }
                break;
        }
    }
}
