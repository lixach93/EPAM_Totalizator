package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.service.exception.ServiceException;


import java.util.List;

public interface TeamService {

    boolean addTeam(String leagueIdStr, String teamName) throws ServiceException;
    List<Team> getTeamByLeague(long id) throws ServiceException;
}
