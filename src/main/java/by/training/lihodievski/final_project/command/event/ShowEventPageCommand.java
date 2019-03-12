package by.training.lihodievski.final_project.command.event;


import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;


public class ShowEventPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowEventPageCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();
    private static final String CATEGORY = "category";
    private static final String EVENTS = "events";

    @Override
    public String execute() throws CommandException {
        return null;
    }


    @Override
    public Respond execute1() throws CommandException {
        String category = request.getParameter (CATEGORY);

        List<Event> rates;
        if(!Validation.isNull (category)){
            if(Validation.isString (category)) {
                rates = fillEventsByTeam (category);
            }else {
                request.setAttribute (REQUEST_ATTRIBUTE_ERROR, ERROR_MESSAGE);
                return new Respond (Respond.PAGE, FORWARD_ERROR_PAGE);
            }
        }else {
            rates = fillEventsByTotal ();
        }
        request.setAttribute (EVENTS, rates);
        return new Respond (Respond.PAGE, FORWARD_EVENT_PAGE);
    }

    private List<Event> fillEventsByTotal() throws CommandException {
        String rate = Rate.TOTAL.getValue ();
        List<Event> rates = new ArrayList<>();
        try {
            rates.addAll (eventService.getEventsByRate (Category.FOOTBALL.getNameCategory (), rate));
            rates.addAll (eventService.getEventsByRate (Category.HOCKEY.getNameCategory (), rate));
            rates.addAll (eventService.getEventsByRate (Category.BASKETBALL.getNameCategory (), rate));
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowEventPageCommand",e);
            throw new CommandException (e);
        }
        return rates;
    }

    private List<Event> fillEventsByTeam(String category) throws CommandException {
        String rate = Rate.TEAM.getValue ();
        try {
            return eventService.getEventsByRate (category, rate);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowEventPageCommand",e);
            throw new CommandException (e);
        }
    }
}

