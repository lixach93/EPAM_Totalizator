package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.util.List;

public class ShowCompetitionRatePageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionRateService competitionRateService = serviceFactory.getCompetitionRateServiceImpl ();

    @Override
    public String execute() throws CommandException {

        request.setAttribute ("activeFour", "active");
        request.setAttribute ("action", "closeCompRate");
        request.setAttribute ("page", "rate");
        List<CompetitionRate> competitionRates ;
        try {
            competitionRates = competitionRateService.getAllUnPaymentRate();
        }catch (ServiceException e){
            throw new CommandException (e);
        }
        request.setAttribute ("competitionRates", competitionRates);
        return "/WEB-INF/view/adminPage.jsp";
    }
}
