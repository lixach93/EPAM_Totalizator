package by.training.lihodievski.final_project.command;

import by.training.lihodievski.final_project.command.exception.CommandException;


import static by.training.lihodievski.final_project.util.Constants.FORWARD_MAIN_PAGE;

public class EmptyCommand extends ActionCommand {

    @Override
    public Respond execute() throws CommandException {
        return new Respond (Respond.PAGE, FORWARD_MAIN_PAGE);
    }
}
