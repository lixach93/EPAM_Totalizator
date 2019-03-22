package by.training.lihodievski.final_project.command.user;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import static by.training.lihodievski.final_project.util.Constants.FORWARD_REGISTRATION_PAGE;

public class ShowRegistrationPageCommand extends ActionCommand {

    @Override
    public Respond execute() throws CommandException {
        return new Respond (Respond.PAGE, FORWARD_REGISTRATION_PAGE);
    }
}
