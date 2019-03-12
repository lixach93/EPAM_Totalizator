package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.ValidationException;

import java.util.List;

public interface LeagueService {

    boolean createLeague(String categoryName, String name) throws ServiceException, ValidationException;

    List<League> getLeagueByCategory(long categoryId) throws ServiceException;
}
