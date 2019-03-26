package by.training.lihodievski.final_project.command;

import by.training.lihodievski.final_project.bean.RoleType;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.command.exception.PermissionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.SESSION_ATTRIBUTE_ROLE;

public abstract class ActionCommand {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public abstract Respond execute() throws CommandException;

    public void checkRole(HttpServletRequest request, RoleType[] permissionRole) throws PermissionException {
       HttpSession session =  request.getSession ();
       String currentRole =  (String) session.getAttribute (SESSION_ATTRIBUTE_ROLE);
       for(RoleType role:permissionRole){
            if(role.getValue ().equals (currentRole)){
                return;
            }
       }
        throw new PermissionException ("Not enough permissions for this operation");
    }
    public void init(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
}
