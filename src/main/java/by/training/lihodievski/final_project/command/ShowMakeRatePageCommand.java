package by.training.lihodievski.final_project.command;

import by.training.lihodievski.final_project.bean.Event;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

public class ShowMakeRatePageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();
    @Override
    public String execute() throws CommandException {
        long rateId = Long.parseLong (request.getParameter ("compRateId"));
        try {
            Event competitionRate = eventService.getRateById (rateId);
            request.setAttribute ("competitionRate",competitionRate);
            return "/WEB-INF/view/makeRatePage.jsp";
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
    }
}
