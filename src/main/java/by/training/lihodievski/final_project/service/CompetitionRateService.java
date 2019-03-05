package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface CompetitionRateService {

    List<CompetitionRate> getRatesByCategoryAndRate(String category, String rate) throws ServiceException;
    CompetitionRate getRateById(long id) throws ServiceException;
    void createCompetitionRate(long opponentFirstId, long opponentSecondId, String typeRate) throws ServiceException;

    List<CompetitionRate> getAllUnPaymentRate() throws ServiceException;

    boolean payments(long competitionRateId) throws ServiceException;
}
