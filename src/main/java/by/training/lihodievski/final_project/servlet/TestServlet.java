package by.training.lihodievski.final_project.servlet;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.factory.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/totalizator")
public class TestServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
// определение команды, пришедшей из JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request,response);
        /*
         * вызов реализованного метода execute() и передача параметров
         * классу-обработчику конкретной команды
         */
        try {
            page = command.execute();
        } catch (CommandException e) {
            throw new ServletException(e);
        }
        if(request.getAttribute ("fail")!=null){
            String f = (String) request.getAttribute ("fail");
            if(f.equals ("fail")){
                return;
            }
        }

        if(request.getAttribute ("ajax")!=null){
            String f = (String) request.getAttribute ("ajax");
            if(f.equals ("sync")){
                return;
            }
        }



// метод возвращает страницу ответа
// page = null; // поэксперементировать!
        if (page != null) {
            if(request.getAttribute ("redirect")!=null){
                response.sendRedirect ( request.getAttribute("redirect").toString ());
            }else{
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
// вызов страницы ответа на запрос

        } else {
// установка страницы c cообщением об ошибке

            request.getRequestDispatcher ("/WEB-INF/error/error.jsp").forward (request,response);
        }
    }
}
