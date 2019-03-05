package by.training.lihodievski.final_project.command.league;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.dao.impl.category.CategoryDaoAbstract;
import by.training.lihodievski.final_project.service.CategoryService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.util.List;

public class AddLeaguePageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CategoryService categoryService = serviceFactory.getCategoryService ();

    @Override
    public String execute() throws CommandException {

        request.setAttribute ("activeOne", "active");
        request.setAttribute ("action", "addLeague");
        request.setAttribute ("page", "league");
        List<Category> categories;
        try {
            categories = categoryService.getAll ();
        }catch (ServiceException e){
            throw new CommandException (e);
        }
        request.setAttribute ("categories", categories);
       return "/WEB-INF/view/adminPage.jsp";
    }
}
