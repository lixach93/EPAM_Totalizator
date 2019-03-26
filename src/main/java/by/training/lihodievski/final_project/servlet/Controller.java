package by.training.lihodievski.final_project.servlet;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.factory.ActionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/totalizator","/totalizator/*"})
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger (Controller.class);
    private static final long serialVersionUID = 3600900093362800280L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Respond respond = null;
        ActionFactory client = new ActionFactory ();
        ActionCommand command = client.defineCommand (request, response);

        try {
            respond = command.execute ();
        } catch (CommandException e) {
            LOGGER.error ("Exception in Controller.class ", e);
            throw new ServletException (e);
        }
        switch (respond.getStatus ()) {
            case Respond.PAGE:
                RequestDispatcher dispatcher = getServletContext ().getRequestDispatcher (respond.getValue ());
                dispatcher.forward (request, response);
                break;
            case Respond.REDIRECT:
                response.sendRedirect (respond.getValue ());
                break;
        }
    }
}
