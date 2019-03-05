package by.training.lihodievski.final_project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand extends ActionCommand {

    @Override
    public String execute() {
        return "/WEB-INF/view/main.jsp";
    }
}
