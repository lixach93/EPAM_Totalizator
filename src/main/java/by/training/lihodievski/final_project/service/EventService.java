package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.ValidationException;

import java.util.List;

public interface EventService {

    List<Event> getEventsByRate(String category, String rate) throws ServiceException;
    Event getRateById(long id) throws ServiceException;
    void createCompetitionRate(long opponentFirstId, long opponentSecondId, String typeRate) throws ServiceException;

    List<Event> getAllUnPaymentRate() throws ServiceException;

    boolean payments(long competitionRateId) throws ServiceException;

    List<Event> getAllActiveEvent() throws ServiceException;

    boolean addPercent(String eventIdStr, String percentStr) throws ServiceException, ValidationException;
}
