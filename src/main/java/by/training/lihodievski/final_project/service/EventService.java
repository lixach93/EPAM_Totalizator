package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface EventService {

    List<Event> getEventsByRate(Rate rate, int numberPage) throws ServiceException;
    List<Event> getEventsByCategory(String category, int numberPage) throws ServiceException;
    int getCountPage(Rate rate) throws ServiceException;
    int getCountPage(String categoryName) throws ServiceException;
    int getCountPageUnPaymentEvents() throws ServiceException;
    boolean createEvent(String teamFirstIdStr, String teamSecondIdStr, String typeRate) throws ServiceException;
    List<Event> getUnPaymentEvents(int numberPage) throws ServiceException;
    boolean payments(String eventIdStr) throws ServiceException;
    List<Event> getAllActiveEvent() throws ServiceException;
    boolean addPercent(String eventIdStr, String percentStr) throws ServiceException;


}
