package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.*;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.betting.BettingDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.competition_rate.CompetitionRateAbstract;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.bean.Rate.TEAM;

public class CompetitionRateServiceImpl implements CompetitionRateService {

    private final static CompetitionRateServiceImpl INSTANCE = new CompetitionRateServiceImpl ();
    private final DaoFactory factory = DaoFactory.getInstance ();
    private final CompetitionRateAbstract competitionRateDao = factory.getCompetitionRateDao ();
    private final BettingDaoAbstract bettingDao = factory.getBettingDao ();

    public static CompetitionRateService getInstance() {
        return INSTANCE;
    }

    private CompetitionRateServiceImpl() {
    }

    @Override
    public List<CompetitionRate> getRatesByCategoryAndRate(String category, String rate) throws ServiceException {
        try{
            return competitionRateDao.getRatesByCategoryAndRate (category,rate);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }

    @Override
    public CompetitionRate getRateById(long id) throws ServiceException {
        try {
            return competitionRateDao.getCompetitionRateById (id);
        } catch (DaoException e) {
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
            competitionRateDao.createCompetitionRate (opponentFirstId, opponentSecondId ,rate);
        } catch (DaoException e) {
            throw new ServiceException (e);
        }

    }

    @Override
    public List<CompetitionRate> getAllUnPaymentRate() throws ServiceException {
        try {
            return competitionRateDao.getUnPaymentRate();
        } catch (DaoException e) {
            throw new ServiceException (e);
        }
    }

    @Override
    public boolean payments(long competitionRateId) throws ServiceException {
        try {
            CompetitionRate competitionRate = competitionRateDao.getCompetitionRateById (competitionRateId);
            if(competitionRate.isPayment ()){
                return false;
            }else {
                Rate rate = competitionRate.getRate ();
                Competition competition = competitionRate.getCompetition ();
                Double money = bettingDao.getMoneyForCompRate (competitionRate);
                List<Betting> bettingList = bettingDao.getAllBettingByCompRate (competitionRate);
                List<Betting> winner = new ArrayList<> ();
                for (Betting currentBetting : bettingList) {
                    checkWin (winner, currentBetting, competition, rate);
                }
                double winMoney = money / winner.size ();

                competitionRate.setPayment (true);
                bettingDao.setWinner (winner, winMoney, competitionRate);
                return true;
            }
        }catch (DaoException e){
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
