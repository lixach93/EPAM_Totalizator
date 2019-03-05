package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.EmptyCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand current = new EmptyCommand ();
        String action = request.getParameter ("command");
        System.out.println (action);
        if (action == null || action.isEmpty ()) {
            return current;
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