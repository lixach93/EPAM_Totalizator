package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.Opponent;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface OpponentService {

    void addOpponent(long leagueId, String teamName) throws ServiceException;

    List<Opponent> getOpponentByLeague(long id) throws ServiceException;
}
