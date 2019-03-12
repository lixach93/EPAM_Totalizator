package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Betting;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.util.ValidationException;

import java.util.List;

public interface BettingService {

    boolean makeRate(long userId, String competitionRateId, String opponent, String money) throws ServiceException, ValidationException, UserException;
    boolean makeRate(long userId, String competitionRateId, String firstScore, String secondScore, String money) throws ServiceException, ValidationException, UserException;
    List<Betting> getActiveBettingForUser(long userId) throws  ServiceException;
    List<Betting> getResultBettingForUser(long userId) throws ServiceException;
}
