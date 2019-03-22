package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.EmptyCommand;
import by.training.lihodievski.final_project.util.UrlEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand current = new EmptyCommand ();
        String action = request.getParameter ("command");
        String pathInfo = request.getPathInfo ();
        System.out.println (action);
        if (action == null || action.isEmpty () ) {
            if(pathInfo == null || pathInfo.isEmpty ()) {
                return current;
            }else{
                action = UrlEncoder.getPath (pathInfo);
            }
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf (action.toUpperCase ());
            currentEnum.initCommand(request, response);
            current = currentEnum.getCurrentCommand ();
        } catch (IllegalArgumentException e) {
            //request.setAttribute ("wrongAction", actionMessageManager.getProperty ("message.wrongaction"));
        }
        return current;
    }
}