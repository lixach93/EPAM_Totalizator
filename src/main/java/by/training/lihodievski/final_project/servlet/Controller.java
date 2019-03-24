package by.training.lihodievski.final_project.servlet;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.factory.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/totalizator","/totalizator/*"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 7301674936598633360L;

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
