package by.training.lihodievski.final_project.service;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.service.exception.ServiceException;

import java.util.List;

public interface CompetitionService {

    List<Competition> getCompetition() throws ServiceException;
    Competition closeCompetition(String competitionIdStr) throws ServiceException;
}
