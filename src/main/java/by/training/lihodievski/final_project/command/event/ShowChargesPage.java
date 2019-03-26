package by.training.lihodievski.final_project.command.event;

import by.training.lihodievski.final_project.bean.Bet;
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

import javax.servlet.http.HttpSession;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ShowChargesPage extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowChargesPage.class);
    private static final String EVENTS = "events";
    private static final String CHARGES = "charges";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.MODERATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
        }
        HttpSession session = request.getSession ();
        String numberPageStr =  request.getParameter (PARAMETER_PAGE);
        int numberPage;
        if(numberPageStr != null){
            numberPage = (Integer.parseInt (numberPageStr)-1)*2;
        }else{
            numberPage = 0;
        }
        int countPage;
        List<Event> events;
        try {
            countPage =  eventService.getCountPageClosedEvents();
            events = eventService.getClosedEvents(numberPage);
        }catch (ServiceException e){
            LOGGER.error ("Exception in ShowChargesPage.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_SIZE, events.size ());
        request.setAttribute (REQUEST_ATTRIBUTE_COUNT_PAGE, countPage);
        request.setAttribute (EVENTS, events);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, CHARGES);
        return new Respond (Respond.PAGE, FORWARD_MODERATOR_PAGE);
    }
}
