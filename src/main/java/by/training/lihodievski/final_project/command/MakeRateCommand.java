package by.training.lihodievski.final_project.command;

import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.BettingService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import javax.servlet.http.HttpSession;

public class MakeRateCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BettingService bettingServiceImpl = serviceFactory.getBettingServiceImpl ();

    @Override
    public String execute() throws CommandException {
        int opponent = Integer.parseInt (request.getParameter ("opponent"));
        double money = Double.parseDouble (request.getParameter ("money"));
        HttpSession session = request.getSession (false);
        long userId = (long) session.getAttribute ("userId");
        long competitionRateId = Long.parseLong (request.getParameter ("competitionRateId"));
        try{
            bettingServiceImpl.makeRate(userId, competitionRateId, opponent, money);
        }catch (ServiceException e){
            throw new CommandException (e);
        }
        String page = request.getParameter ("redirect");
        request.setAttribute ("redirect",page);
        return "mdasda";
    }
}
