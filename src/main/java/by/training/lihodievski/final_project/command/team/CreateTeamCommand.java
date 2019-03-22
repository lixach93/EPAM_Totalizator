package by.training.lihodievski.final_project.command.team;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.TeamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class CreateTeamCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (CreateTeamCommand.class);
    private static final String LEAGUE_ID = "leagueId";
    private static final String TEAM_NAME = "teamName";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private TeamService teamService = serviceFactory.getOpponentService();



    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }

        String leagueIdStr = request.getParameter (LEAGUE_ID);
        String teamName = request.getParameter (TEAM_NAME);
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        boolean status;
        try {
            status = teamService.addTeam (leagueIdStr, teamName);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in CreateTeamCommand.class ", e);
            throw new CommandException (e);
        }
        HttpSession session = request.getSession ();
        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
