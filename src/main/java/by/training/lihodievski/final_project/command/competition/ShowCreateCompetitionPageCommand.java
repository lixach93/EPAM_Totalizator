package by.training.lihodievski.final_project.command.competition;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.CategoryService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;

import java.util.List;

public class ShowCreateCompetitionPageCommand extends ActionCommand {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CategoryService categoryService = serviceFactory.getCategoryService ();
    @Override
    public String execute() throws CommandException {
        request.setAttribute ("activeThree", "active");
        request.setAttribute ("page", "competition");
        request.setAttribute ("action", "createCompetition");
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
