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
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private CategoryService categoryService = serviceFactory.getCategoryService ();

    @Override
    public String execute() throws CommandException {

        return null;
    }

    @Override
    public Respond execute1() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION, ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_ONE, ACTIVE);
        request.setAttribute (REQUEST_ATTRIBUTE_BLOCK, LEAGUE);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, CREATE_LEAGUE);

        List<Category> categories;
        try {
            categories = categoryService.getCategories ();
        }catch (ServiceException e){
            LOGGER.error ("Exception in ShowCreateLeaguePageCommand.class ", e);
            throw new CommandException (e);
        }
        request.setAttribute (CATEGORIES, categories);
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
