package by.training.lihodievski.final_project.command.event;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ShowActiveEventPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowActiveEventPageCommand.class);
    private static final String EVENTS = "events";
    private static final String ACTIVE_EVENT = "activeEvent";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public String execute() throws CommandException {

        return "/WEB-INF/view/adminPage.jsp";
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.MODERATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
        }

        List<Event> events;
        try {
            events = eventService.getAllActiveEvent();
        }catch (ServiceException e){
            LOGGER.error ("Error in ShowActiveEventPageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (EVENTS, events);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK, EVENT);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, ACTIVE_EVENT);
        request.setAttribute (REQUEST_ATTRIBUTE_LINK_ONE, ACTIVE);
        return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
    }
}
