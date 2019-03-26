package by.training.lihodievski.final_project.command.event;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;

public class ShowScoreEventPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowScoreEventPageCommand.class);
    private static final String EVENTS = "events";

    @Override
    public Respond execute() throws CommandException {
        String numberPageStr =  request.getParameter (PARAMETER_PAGE);
        int numberPage;
        if(numberPageStr != null){
            numberPage = (Integer.parseInt (numberPageStr)-1)*2;
        }else{
            numberPage = 0;
        }
        List<Event> events;
        int countPage;
        EventService eventService = ServiceFactory.getInstance ().getEventService ();
        try {
            countPage =  eventService.getCountPage (Rate.TOTAL);
            events = eventService.getEventsByRate (Rate.TOTAL, numberPage);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowScoreEventPageCommand.class ", e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_COUNT_PAGE, countPage);
        request.setAttribute (EVENTS, events);
        return new Respond (Respond.PAGE, FORWARD_EVENT_PAGE);
    }




}
