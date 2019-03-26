package by.training.lihodievski.final_project.service.impl;

import by.training.lihodievski.final_project.bean.League;
import by.training.lihodievski.final_project.bean.Team;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.factory.DaoFactory;
import by.training.lihodievski.final_project.dao.impl.league.LeagueDaoAbstract;
import by.training.lihodievski.final_project.dao.impl.team.TeamDaoAbstract;
import by.training.lihodievski.final_project.service.TeamService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private static final Logger LOGGER = LogManager.getLogger (TeamServiceImpl.class);
    private static final TeamServiceImpl INSTANCE = new TeamServiceImpl ();
    private final DaoFactory daoFactory = DaoFactory.getInstance ();
    private LeagueDaoAbstract leagueDao = daoFactory.getLeagueDao ();
    private final TeamDaoAbstract teamDao = daoFactory.getTeamDao ();

    private TeamServiceImpl() {
    }

    public static TeamService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addTeam(String leagueIdStr, String teamName) throws ServiceException {
        if(!Validator.isId (leagueIdStr) || !Validator.isTeam (teamName)){
            return false;
        }
        long leagueId = Long.parseLong (leagueIdStr);
        try {
            League league = leagueDao.getLeagueById(leagueId);
            Team team = new Team ();
            team.setLeague (league);
            team.setTeamName (teamName);
            teamDao.insert (team);
        } catch (DaoException e) {
            LOGGER.error ("Exception in addTeam in TeamServiceImpl.class ", e);
            throw new ServiceException (e);
        }
        return true;
    }

    @Override
    public List<Team> getTeamByLeague(long id) throws ServiceException {
        League league = new League ();
        league.setId (id);
        try {
            return teamDao.getTeamByLeague (league);
        } catch (DaoException e) {
            LOGGER.error ("Exception in getTeamByLeague in TeamServiceImpl.class ", e);
            throw new ServiceException (e);
        }
    }
}
