package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.io.IOException;

public class CloseCompetitionRateCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public String execute() throws CommandException {
        long competitionRateId = Long.parseLong (request.getParameter ("competitionRateId"));
        boolean status;
        try {
            status = eventService.payments(competitionRateId);
            if(status){
                response.getWriter ().write ("выплаты произведены");
            }else{
                response.getWriter ().write ("уже произведены выплаты");
            }
        } catch (ServiceException e) {
            throw new CommandException (e);
        } catch (IOException e) {
            throw new CommandException (e);
        }
        request.setAttribute ("ajax","sync");
        return "dsa";
    }
}
