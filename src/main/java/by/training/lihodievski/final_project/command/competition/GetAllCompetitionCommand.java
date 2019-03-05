package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.service.CompetitionService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.impl.CompetitionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Locale;

public class GetAllCompetitionCommand extends ActionCommand {

    @Override
    public String execute() {


        return "/WEB-INF/view/competition.jsp";
    }
}
