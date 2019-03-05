package by.training.lihodievski.final_project.command;

import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.CompetitionRateServiceImpl;

public class ShowMakeRatePageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionRateService competitionRateService = serviceFactory.getCompetitionRateServiceImpl ();
    @Override
    public String execute() throws CommandException {
        long rateId = Long.parseLong (request.getParameter ("compRateId"));
        try {
            CompetitionRate competitionRate = competitionRateService.getRateById (rateId);
            request.setAttribute ("competitionRate",competitionRate);
            return "/WEB-INF/view/makeRatePage.jsp";
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
    }
}
