package by.training.lihodievski.final_project.command.event;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.service.EventService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.ValidationException;

import java.io.IOException;

import static by.training.lihodievski.final_project.util.Constants.PARAMETER_EVENT_ID;

public class AddPercentToEventCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private EventService eventService = serviceFactory.getEventService ();

    @Override
    public String execute() throws CommandException {
        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        String eventIdStr = request.getParameter (PARAMETER_EVENT_ID);
        String percentStr = request.getParameter ("percent");
        boolean status;
        try {
            status = eventService.addPercent(eventIdStr, percentStr);
            if(status){
                response.getWriter ().write ("Процент назначен");
            }else{
                response.getWriter ().write ("Уже имеются проценты");
            }
        } catch (ServiceException e) {
            throw new CommandException (e);
        }catch (ValidationException e) {
            try {
                response.getWriter ().write ("Данные не корректны");
            } catch (IOException e1) {
                throw new CommandException (e);
            }
        } catch (IOException e) {
            throw new CommandException (e);
        }
        return new Respond (Respond.AJAX);

    }
}
