package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface LeagueService {

    void addLeague(long id, String name) throws ServiceException;

    List<League> getLeagueByCategory(long id) throws ServiceException;
}
