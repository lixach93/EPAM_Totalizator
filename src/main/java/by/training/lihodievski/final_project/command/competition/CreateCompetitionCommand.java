package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

public class CreateCompetitionCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionRateService competitionRateService = serviceFactory.getCompetitionRateServiceImpl ();

    @Override
    public String execute() throws CommandException {
        long opponentOneId = Long.parseLong (request.getParameter ("opponentOneId"));
        long opponentTwoId = Long.parseLong (request.getParameter ("opponentTwoId"));
        String typeRate = request.getParameter ("rate");
        try {
            competitionRateService.createCompetitionRate (opponentOneId, opponentTwoId, typeRate);
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
        String page = request.getParameter ("redirect");
        request.setAttribute ("redirect",page);
        return "/null";


    }
}
