package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface LeagueService {

    boolean createLeague(String categoryName, String leagueName) throws ServiceException;
    List<League> getLeagueByCategory(long categoryId) throws ServiceException;
    List<League> getLeagues() throws ServiceException;
    boolean deleteLeague(String leagueIdStr) throws ServiceException;
}
