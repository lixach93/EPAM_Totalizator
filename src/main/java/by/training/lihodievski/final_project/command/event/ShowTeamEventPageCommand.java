package by.training.lihodievski.final_project.command.event;


import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.UrlEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;


public class ShowTeamEventPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowTeamEventPageCommand.class);
    private static final String EVENTS = "events";

    @Override
    public Respond execute() throws CommandException {
        String category = UrlEncoder.getAction (request.getPathInfo ());
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
            countPage =  eventService.getCountPage (category);
            events = eventService.getEventsByCategory(category, numberPage);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowTeamEventPageCommand",e);
            throw new CommandException (e);
        }catch (IllegalArgumentException e){
            LOGGER.error ("Exception in category VALUE ",e);
            return new Respond (Respond.PAGE, FORWARD_MAIN_PAGE);
        }

        request.setAttribute ("countPage", countPage);
        request.setAttribute (EVENTS, events);
        return new Respond (Respond.PAGE, FORWARD_EVENT_PAGE);
    }



}

