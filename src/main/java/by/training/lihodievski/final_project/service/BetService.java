package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Bet;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;

import java.util.List;

public interface BetService {

    boolean makeRate(long userId, String competitionRateId, String opponent, String money) throws ServiceException, UserException;
    boolean makeRate(long userId, String competitionRateId, String firstScore, String secondScore, String money) throws ServiceException, UserException;
    int getCountPage(long userId) throws  ServiceException;
    List<Bet> getResultBetForUser(long userId) throws ServiceException;
    List<Bet> getActiveBet(long userId, int numberPage) throws ServiceException;
}
