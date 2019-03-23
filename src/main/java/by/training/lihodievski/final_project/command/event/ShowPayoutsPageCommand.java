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

public class ShowPayoutsPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowPayoutsPageCommand.class);
    private static final String PAYOUTS = "payouts";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();


    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String numberPageStr =  request.getParameter (PARAMETER_PAGE);
        int numberPage;
        if(numberPageStr != null){
            numberPage = (Integer.parseInt (numberPageStr)-1)*2;
        }else{
            numberPage = 0;
        }
        List<Event> events;
        int countPage;
        try {
            countPage = eventService.getCountPageUnPaymentEvents ();
            events = eventService.getUnPaymentEvents (numberPage);
        }catch (ServiceException e){
            LOGGER.error ("Exception in ShowPayoutsPageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_SIZE, events.size ());
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_FOUR, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, PAYOUTS);
        request.setAttribute (REQUEST_ATTRIBUTE_COUNT_PAGE, countPage);
        request.setAttribute (EVENTS, events);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
