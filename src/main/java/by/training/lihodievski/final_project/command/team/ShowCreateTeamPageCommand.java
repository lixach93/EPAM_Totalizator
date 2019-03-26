package by.training.lihodievski.final_project.command.team;

import by.training.lihodievski.final_project.bean.Category;
import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.CategoryService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_ADMIN_PAGE;

public class ShowCreateTeamPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowCreateTeamPageCommand.class);
    private static final String CREATE_TEAM = "createTeam";
    private static final String CATEGORIES = "categories";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CategoryService categoryService = serviceFactory.getCategoryService ();

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        List<Category> categories;
        try {
            categories = categoryService.getCategories ();
        }catch (ServiceException e){
            LOGGER.error ("Exception in ShowCreateTeamPageCommand.class ", e);
            throw new CommandException (e);
        }

        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_TWO, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, CREATE_TEAM);
        request.setAttribute (CATEGORIES, categories);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
