package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.*;
import by.training.lihodievski.final_project.command.bet.MakeBetCommand;
import by.training.lihodievski.final_project.command.bet.ShowActiveBetPageCommand;
import by.training.lihodievski.final_project.command.bet.ShowResultPageCommand;
import by.training.lihodievski.final_project.command.competition.*;
import by.training.lihodievski.final_project.command.event.*;
import by.training.lihodievski.final_project.command.league.*;
import by.training.lihodievski.final_project.command.locale.ChangeLocaleCommand;
import by.training.lihodievski.final_project.command.team.CreateTeamCommand;
import by.training.lihodievski.final_project.command.team.GetTeamsByLeagueIdCommand;
import by.training.lihodievski.final_project.command.team.ShowCreateTeamPageCommand;
import by.training.lihodievski.final_project.command.user.*;
import by.training.lihodievski.final_project.command.user.ChangeRoleCommand;
import by.training.lihodievski.final_project.command.user.ShowAdminPageCommand;
import by.training.lihodievski.final_project.command.user.ShowUsersPageCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum CommandEnum {

    ACTIVEBET {
        {
            this.command = new ShowActiveBetPageCommand ();
        }
    },
    ACTIVEEVENT {
        {
            this.command = new ShowActiveEventPageCommand ();
        }
    },
    ADDPERCENTTOEVENT {
        {
            this.command = new AddPercentToEventCommand ();
        }
    },
    ADMIN {
        {
            this.command = new ShowAdminPageCommand ();
        }
    },
    CHANGELOCALE {
        {
            this.command = new ChangeLocaleCommand ();
        }
    },
    CHANGEROLE {
        {
            this.command = new ChangeRoleCommand ();
        }
    },
    CHARGES {
        {
            this.command = new ShowChargesPage ();
        }
    },
    CLOSECOMPETITION {
        {
            this.command = new ShowCloseCompetitionPage ();
        }
    },
    CLOSECOMPETITIONACTION {
        {
            this.command = new CloseCompetitionCommand ();
        }
    },
    CLOSEEVENT {
        {
            this.command = new CloseEventCommand ();
        }
    },
    CREATECOMPETITION {
        {
            this.command = new ShowCreateCompetitionPageCommand ();
        }
    },
    CREATECOMPETITIONACTION {
        {
            this.command = new CreateCompetitionCommand ();
        }
    },
    CREATELEAGUE {
        {
            this.command = new ShowCreateLeaguePageCommand ();
        }
    },
    CREATELEAGUEACTION {
        {
            this.command = new CreateLeagueCommand ();
        }
    },
    CREATETEAM {
        {
            this.command = new ShowCreateTeamPageCommand ();
        }
    },
    CREATETEAMACTION {
        {
            this.command = new CreateTeamCommand ();
        }
    },
    DELETELEAGUE {
        {
            this.command = new ShowDeleteLeaguePageCommand ();
        }
    },
    DELETELEAGUEACTION {
        {
            this.command = new DeleteLeagueCommand ();
        }
    },
    FILLBALANCE {
        {
            this.command = new FillBalanceCommand ();
        }
    },
    GETLEAGUESBYCATEGORY {
        {
            this.command = new GetLeaguesByCategoryCommand ();
        }
    },
    GETTEAMSBYLEAGUEID {
        {
            this.command = new GetTeamsByLeagueIdCommand ();
        }
    },
    LOGIN {
        {
            this.command = new ShowLoginPageCommand ();
        }
    },
    LOGINACTION {
        {
            this.command = new LoginCommand ();
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
    MODERATOR {
        {
            this.command = new ShowModeratorPageCommand ();
        }
    },
    PAYOUTS {
        {
            this.command = new ShowPayoutsPageCommand ();
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
    REGISTRATIONACTION {
        {
            this.command = new RegistrationCommand ();
        }
    },
    RESULT {
        {
            this.command = new ShowResultPageCommand ();
        }
    },
    SCOREEVENT {
        {
            this.command = new ShowScoreEventPageCommand ();
        }
    },
    TEAMEVENT {
        {
            this.command = new ShowTeamEventPageCommand ();
        }
    },
    UPDATEBALANCE {
        {
            this.command = new ShowUpdateBalancePage ();
        }
    },
    USERS {
        {
            this.command = new ShowUsersPageCommand ();
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
