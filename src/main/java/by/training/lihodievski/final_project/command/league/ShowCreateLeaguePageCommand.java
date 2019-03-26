package by.training.lihodievski.final_project.command.league;

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

public class ShowCreateLeaguePageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowCreateLeaguePageCommand.class);
    private static final String CREATE_LEAGUE = "createLeague";
    private static final String CATEGORIES = "categories";

    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            LOGGER.error (e.getMessage ());
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        CategoryService categoryService = ServiceFactory.getInstance ().getCategoryService ();
        List<Category> categories;
        try {
            categories = categoryService.getCategories ();
        }catch (ServiceException e){
            LOGGER.error ("Exception in ShowCreateLeaguePageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (CATEGORIES, categories);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, CREATE_LEAGUE);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
