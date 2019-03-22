package by.training.lihodievski.final_project.command.event;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.SESSION_ATTRIBUTE_STATUS;
import static by.training.lihodievski.final_project.util.Constants.STATUS_UN_SUCCESS;

public class AddPercentToEventCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (AddPercentToEventCommand.class);
    private static final String PERCENT = "percent";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public Respond execute() throws CommandException {
        String eventIdStr = request.getParameter (PARAMETER_EVENT_ID);
        String percentStr = request.getParameter (PERCENT);
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        boolean status;
        try {
            status = eventService.addPercent(eventIdStr, percentStr);
        } catch (ServiceException e) {
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
