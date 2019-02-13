package by.training.lihodievski.final_project.servlet;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.Competition;

import by.training.lihodievski.final_project.dao.GenericDao;
import by.training.lihodievski.final_project.dao.exception.DaoException;
import by.training.lihodievski.final_project.dao.impl.CategoryDaoImpl;
import by.training.lihodievski.final_project.dao.impl.CompetitionDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@WebServlet("/controller")
public class Controller extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GenericDao<Category> categoryGenericDao = CategoryDaoImpl.getInstance ();
       List<Category> competitionList = null;
        try {
            competitionList = categoryGenericDao.getAll ();
        } catch (DaoException e) {
            e.printStackTrace ();
        }
        request.setAttribute ("competiton",competitionList);
        request.getRequestDispatcher ("WEB-INF/view/competition.jsp").forward (request,response);
        }

}
