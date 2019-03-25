package by.training.lihodievski.final_project.command.user;


import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.bean.User;
import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;
import by.training.lihodievski.final_project.service.UserService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.training.lihodievski.final_project.util.Constants.*;
import static by.training.lihodievski.final_project.util.Constants.ACTIVE;

public class ShowUsersPageCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (ShowUsersPageCommand.class);
    private static final String USERS = "users";
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private UserService userServiceImpl = serviceFactory.getUserService ();


    @Override
    public Respond execute() throws CommandException {
        try {
            checkRole (request,new RoleType[]{RoleType.ADMINISTRATOR});
        } catch (PermissionException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_PERMISSION,ERROR_PERMISSION_INFO);
            return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
        }
        String numberPageStr =  request.getParameter (PARAMETER_PAGE);
        int numberPage;
        if(numberPageStr != null){
            numberPage = (Integer.parseInt (numberPageStr)-1)*2;
        }else{
            numberPage = 0;
        }
        int countPage;
        List<User> users;
        try {
            countPage = userServiceImpl.getCountUsers();
            users = userServiceImpl.getUsers(numberPage);
        } catch (ServiceException e) {
            LOGGER.error ("Exception in ShowUsersPageCommand.class ", e);
            throw new CommandException (e);
        }
        ;
        request.setAttribute (REQUEST_ATTRIBUTE_USERS, users);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTIVE_FIVE, ACTIVE);;
        request.setAttribute (REQUEST_ATTRIBUTE_COUNT_PAGE, countPage);
        request.setAttribute (REQUEST_ATTRIBUTE_ACTION, USERS );
        return new Respond (Respond.PAGE, FORWARD_ADMIN_PAGE);
    }
}
