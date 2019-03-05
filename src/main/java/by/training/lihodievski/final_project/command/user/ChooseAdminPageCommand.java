package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseAdminPageCommand extends ActionCommand {
    @Override
    public String execute() throws CommandException {
        String page = request.getParameter ("page");
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute ("permissionMessage","Only for admin");
        }

        switch (page){
            case "league":
                request.setAttribute ("page",page);
                request.setAttribute ("activeOne","active");
                break;
            case "opponent":
                request.setAttribute ("page",page);
                request.setAttribute ("activeTwo","active");
                break;
            case "competition":
                request.setAttribute ("page",page);
                request.setAttribute ("activeThree","active");
                break;
            case "rate":
                request.setAttribute ("page",page);
                request.setAttribute ("activeFour","active");
                break;
            default:
                try {
                    request.setAttribute ("fail","fail");
                    response.sendError (HttpServletResponse.SC_NOT_FOUND);
                } catch (IOException e) {
                    e.printStackTrace ();
                }
        }

        return "/WEB-INF/view/adminPage.jsp";
    }
}
