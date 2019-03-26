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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.ERROR_PERMISSION_INFO;

public class ShowCloseCompetitionPage extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowCreateCompetitionPageCommand.class);
    private static final String CLOSE_COMPETITION = "closeCompetition";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionService competitionService = serviceFactory.getCompetitionService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        List<Competition> competitions;
        try{
            competitions = competitionService.getCompetition ();
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowCloseCompetitionPage.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_SIZE, competitions.size ());
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_THREE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, CLOSE_COMPETITION );
        request.setAttribute (COMPETITIONS, competitions);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
