package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.*;
import by.training.lihodievski.final_project.command.bet.MakeBetCommand;
import by.training.lihodievski.final_project.command.bet.ShowActiveBetPageCommand;
import by.training.lihodievski.final_project.command.bet.ShowResultBettingPageCommand;
import by.training.lihodievski.final_project.command.competition.*;
import by.training.lihodievski.final_project.command.event.*;
import by.training.lihodievski.final_project.command.league.CreateLeagueCommand;
import by.training.lihodievski.final_project.command.league.ShowCreateLeaguePageCommand;
import by.training.lihodievski.final_project.command.league.GetLeaguesByCategoryCommand;
import by.training.lihodievski.final_project.command.locale.ChangeLocaleCommand;
import by.training.lihodievski.final_project.command.team.CreateTeamCommand;
import by.training.lihodievski.final_project.command.team.GetTeamsByLeagueIdCommand;
import by.training.lihodievski.final_project.command.team.ShowCreateTeamPageCommand;
import by.training.lihodievski.final_project.command.user.*;
import by.training.lihodievski.final_project.command.user.admin.ChangeRoleCommand;
import by.training.lihodievski.final_project.command.user.admin.ShowAdminPageCommand;
import by.training.lihodievski.final_project.command.user.admin.ShowUsersPageCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum CommandEnum {

    USERS{
        {
            this.command = new ShowUsersPageCommand ();
        }
    },
    FILLBALANCE{
        {
            this.command = new FillBalanceCommand ();
        }
    },
    UPDATEBALANCE{
        {
            this.command = new ShowUpdateBalancePage ();
        }
    },
    CREATELEAGUEACTION {
        {
            this.command = new CreateLeagueCommand ();
        }
    },
    CREATELEAGUE {
        {
            this.command = new ShowCreateLeaguePageCommand ();
        }
    },
    LOGINACTION {
        {
            this.command = new LoginCommand ();
        }
    },
    CHANGEROLE{
        {
            this.command = new ChangeRoleCommand ();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand ();
        }
    },
    MAKEBET {
        {
            this.command = new MakeBetCommand ();
        }
    },
    REGISTRATIONACTION {
        {
            this.command = new RegistrationCommand ();
        }
    },
    ADMIN {
        {
            this.command = new ShowAdminPageCommand ();
        }
    },
    TEAMEVENT {
        {
            this.command = new ShowTeamEventPageCommand ();
        }
    },
    SCOREEVENT {
        {
            this.command = new ShowScoreEventPageCommand ();
        }
    },
    LOGIN {
        {
            this.command = new ShowLoginPageCommand ();
        }
    },
    PERSONAL {
        {
            this.command = new ShowPersonalPageCommand ();
        }
    },
    REGISTRATION {
        {
            this.command = new ShowRegistrationPageCommand ();
        }
    },
    CREATETEAM{
        {
            this.command = new ShowCreateTeamPageCommand ();
        }
    },
    CREATETEAMACTION{
        {
            this.command = new CreateTeamCommand ();
        }
    },
    GETLEAGUESBYCATEGORY{
        {
            this.command = new GetLeaguesByCategoryCommand ();
        }
    },
    CREATECOMPETITION{
        {
            this.command = new ShowCreateCompetitionPageCommand ();
        }
    },
    CREATECOMPETITIONACTION{
        {
            this.command = new CreateCompetitionCommand ();
        }
    },
    GETTEAMSBYLEAGUEID {
        {
            this.command = new GetTeamsByLeagueIdCommand ();
        }
    },
    CLOSECOMPETITION{
        {
            this.command = new ShowCloseCompetitionPage ();
        }
    },
    CLOSECOMPETITIONACTION{
        {
            this.command = new CloseCompetitionCommand ();
        }
    },
    PAYOUTS {
        {
            this.command = new ShowPayoutsPageCommand ();
        }
    },
    CLOSEEVENT{
        {
            this.command = new CloseEventCommand ();
        }
    },
    CHANGELOCALE{
        {
            this.command = new ChangeLocaleCommand ();
        }
    },
    ACTIVEBET{
        {
            this.command = new ShowActiveBetPageCommand ();
        }
    },
    SHOWRESULTBETTINGPAGE{
        {
            this.command = new ShowResultBettingPageCommand ();
        }
    },
    MODERATOR{
        {
            this.command = new ShowModeratorPageCommand();
        }
    },
    SHOWACTIVEEVENTPAGE{
        {
            this.command = new ShowActiveEventPageCommand ();
        }
    },
    ADDPERCENTTOEVENT{
        {
            this.command = new AddPercentToEventCommand ();
        }
    },
    SHOWUSERPAGE{
        {
            this.command = new ShowUserPageCommand ();
        }
    };


    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

    public void initCommand(HttpServletRequest request, HttpServletResponse response) {
        this.command.init (request, response);
    }
    }
