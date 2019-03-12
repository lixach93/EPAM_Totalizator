package by.training.lihodievski.final_project.command.betting;

import by.training.lihodievski.final_project.command.ActionCommand;
import by.training.lihodievski.final_project.command.Respond;
import by.training.lihodievski.final_project.command.exception.CommandException;
import by.training.lihodievski.final_project.service.BettingService;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.service.exception.UserException;
import by.training.lihodievski.final_project.service.factory.ServiceFactory;
import by.training.lihodievski.final_project.util.Validation;
import by.training.lihodievski.final_project.util.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.training.lihodievski.final_project.util.Constants.*;

public class MakeBettingCommand extends ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger (MakeBettingCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance ();
    private BettingService bettingServiceImpl = serviceFactory.getBettingService ();
    private static final String PARAMETER_MONEY = "money";
    private static final String PARAMETER_FIRST_SCORE = "firstScore";
    private static final String PARAMETER_SECOND_SCORE = "secondScore";

    @Override
    public String execute() throws CommandException {

        return "mdasda";
    }

    @Override
    public Respond execute1() throws CommandException {
        String redirect = request.getParameter (PARAMETER_REDIRECT);
        String moneyStr = request.getParameter (PARAMETER_MONEY);
        String eventIdStr = request.getParameter (PARAMETER_EVENT_ID);
        HttpSession session = request.getSession (false);
        long userId = (long) session.getAttribute (SESSION_ATTRIBUTE_ID);
        boolean status;
        try{
            String opponentStr = request.getParameter (PARAMETER_OPPONENT);
            if(Validation.isNull (opponentStr)){
                String firstScoreStr = request.getParameter (PARAMETER_FIRST_SCORE);
                String secondScoreStr = request.getParameter (PARAMETER_SECOND_SCORE);
                status = bettingServiceImpl.makeRate(userId, eventIdStr, firstScoreStr, secondScoreStr, moneyStr);
            }else{
                status = bettingServiceImpl.makeRate(userId, eventIdStr, opponentStr, moneyStr);
            }
        }catch (ServiceException e){
            LOGGER.error ("Exception in MakeBettingCommand ",e);
            throw new CommandException (e);
        } catch (ValidationException e) {
            request.setAttribute (REQUEST_ATTRIBUTE_BACK, redirect);
            request.setAttribute (REQUEST_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.PAGE, FORWARD_ERROR_PAGE);
        } catch (UserException e) {
            session.setAttribute (SESSION_ATTRIBUTE_ERROR, e.getMessage ());
            return new Respond (Respond.REDIRECT, redirect);
        }
        if(status){
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_SUCCESS );
        }else{
            session.setAttribute (SESSION_ATTRIBUTE_STATUS, STATUS_UN_SUCCESS );
        }
        return new Respond (Respond.REDIRECT, redirect);
    }
}
