package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.EmptyCommand;
import by.training.lihodievski.final_project.util.UrlEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    private static final Logger LOGGER = LogManager.getLogger (ActionFactory.class);
    private static final String COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand current = new EmptyCommand ();
        String action = request.getParameter (COMMAND);
        String pathInfo = request.getPathInfo ();
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
            LOGGER.error ("Exception create enum in ActionCommand.class", e);
            return current;
        }
        return current;
    }
}