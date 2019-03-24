package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.Competition;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpSession;


import static by.training.lihodievski.final_project.util.Constants.*;

public class CloseCompetitionCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (CloseCompetitionCommand.class);
    private static final String COMPETITION_ID = "competitionId";
    private static final String COMPETITION = "competition";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionService competitionService = serviceFactory.getCompetitionService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }

        String  competitionIdStr = request.getParameter (COMPETITION_ID);
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        Competition competition;
        try {
            HttpSession session = request.getSession ();
            competition = competitionService.closeCompetition (competitionIdStr);
            if(!Validator.isNull (competition)){
                session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
                session.setAttribute (COMPETITION, competition );
            }else{
                session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
            }
        } catch (ServiceException e) {
            LOGGER.error ("Exception in CloseCompetitionCommand.class ", e);
            throw new CommandException (e);
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
