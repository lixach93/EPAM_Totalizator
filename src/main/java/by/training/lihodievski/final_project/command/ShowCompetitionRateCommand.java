package by.training.lihodievski.final_project.command;


import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.CompetitionRate;
import by.training.lihodievski.final_project.bean.Rate;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CompetitionRateService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.service.impl.CompetitionRateServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class ShowCompetitionRateCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CompetitionRateService competitionRateService = serviceFactory.getCompetitionRateServiceImpl ();
    private Category enumCategory;
    @Override
    public String execute() throws CommandException {
        String category = request.getParameter ("category");
        List<CompetitionRate> rates;
        if(category != null){
            rates = fillCompetitionRateByTeam (category);
        }else {
            rates = fillCompetitionRateByTotal ();
        }
        request.setAttribute ("rates",rates);
        return "/WEB-INF/view/competitionRate.jsp";
    }

    private List<CompetitionRate> fillCompetitionRateByTotal() throws CommandException {
        String rate = Rate.TOTAL.getValue ();
        List<CompetitionRate> rates = new ArrayList<>();
        try {
            rates.addAll (competitionRateService.getRatesByCategoryAndRate (Category.FOOTBALL.getNameCategory (), rate));
            rates.addAll (competitionRateService.getRatesByCategoryAndRate (Category.HOCKEY.getNameCategory (), rate));
            rates.addAll (competitionRateService.getRatesByCategoryAndRate (Category.BASKETBALL.getNameCategory (), rate));
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
        return rates;
    }

    private List<CompetitionRate> fillCompetitionRateByTeam(String category) throws CommandException {
        String rate = Rate.TEAM.getValue ();
        try {
           return competitionRateService.getRatesByCategoryAndRate (category, rate);
        } catch (ServiceException e) {
            throw new CommandException (e);
        }
    }
}

