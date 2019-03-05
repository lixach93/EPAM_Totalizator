package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.io.IOException;

public class CloseCompetitionCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionService competitionService = serviceFactory.getCompetitionService ();

    @Override
    public String execute() throws CommandException {
        System.out.println ("closecomp");
        int id = Integer.parseInt (request.getParameter ("competitionId"));
        boolean update;
        try {
           update =  competitionService.closeCompetition (id);
            if(update){
                response.getWriter ().write ("update success");
            }else{
                response.getWriter ().write ("update was");
            }
        } catch (ServiceException e) {
            throw new CommandException (e);
        }catch (IOException e){
            throw new CommandException (e);
        }
        request.setAttribute ("ajax","sync");
        return "page";
    }
}
