package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.service.exception.ServiceException;

public interface BettingService {
    void makeRate(long userId, long competitionRateId, int opponent, double money) throws ServiceException;
}
