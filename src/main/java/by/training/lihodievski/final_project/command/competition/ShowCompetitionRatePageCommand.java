package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.util.List;

public class ShowCompetitionRatePageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public String execute() throws CommandException {

        request.setAttribute ("activeFour", "active");
        request.setAttribute ("action", "closeCompRate");
        request.setAttribute ("page", "rate");
        List<Event> events;
        try {
            events = eventService.getAllUnPaymentRate();
        }catch (ServiceException e){
            throw new CommandException (e);
        }
        request.setAttribute ("events", events);
        return "/WEB-INF/view/adminPage.jsp";
    }
}
