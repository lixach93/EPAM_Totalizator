package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.*;
import by.training.lihodievski.final_project.command.betting.MakeBettingCommand;
import by.training.lihodievski.final_project.command.betting.ShowActiveBettingPageCommand;
import by.training.lihodievski.final_project.command.betting.ShowResultBettingPageCommand;
import by.training.lihodievski.final_project.command.competition.*;
import by.training.lihodievski.final_project.command.event.AddPercentToEventCommand;
import by.training.lihodievski.final_project.command.event.ShowActiveEventPageCommand;
import by.training.lihodievski.final_project.command.event.ShowEventPageCommand;
import by.training.lihodievski.final_project.command.league.CreateLeagueCommand;
import by.training.lihodievski.final_project.command.league.ShowCreateLeaguePageCommand;
import by.training.lihodievski.final_project.command.league.GetLeaguesByCategoryCommand;
import by.training.lihodievski.final_project.command.locale.ChangeLocaleCommand;
import by.training.lihodievski.final_project.command.team.CreateTeamCommand;
import by.training.lihodievski.final_project.command.team.GetOpponentsByLeagueIdCommand;
import by.training.lihodievski.final_project.command.team.ShowCreateTeamPageCommand;
import by.training.lihodievski.final_project.command.user.*;
import by.training.lihodievski.final_project.command.user.admin.ChooseAdminPageCommand;
import by.training.lihodievski.final_project.command.user.admin.ShowAdminPageCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum CommandEnum {
    ADDCATEGORYPAGE {
        {
            this.command = new AddCategoryPageCommand ();
        }
    },
    CREATELEAGUE {
        {
            this.command = new CreateLeagueCommand ();
        }
    },
    SHOWCREATELEAGUEPAGE {
        {
            this.command = new ShowCreateLeaguePageCommand ();
        }
    },
    ALLCOMPETITION {
        {
            this.command = new GetAllCompetitionCommand ();
        }
    },
    CHOOSEADMINPAGE {
        {
            this.command = new ChooseAdminPageCommand ();
        }
    },
    LOGIN {
        {
            this.command = new LoginCommand ();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand ();
        }
    },
    MAKEBETTING {
        {
            this.command = new MakeBettingCommand ();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand ();
        }
    },
    SHOWADMINPAGE {
        {
            this.command = new ShowAdminPageCommand ();
        }
    },
    SHOWEVENTPAGE {
        {
            this.command = new ShowEventPageCommand ();
        }
    },
    SHOWLOGINPAGE {
        {
            this.command = new ShowLoginPageCommand ();
        }
    },
    SHOWMAKERATEPAGE {
        {
            this.command = new ShowMakeRatePageCommand ();
        }
    },
    SHOWPERSONALPAGE {
        {
            this.command = new ShowPersonalPageCommand ();
        }
    },
    SHOWREGISTRATIONPAGE {
        {
            this.command = new ShowRegistrationPageCommand ();
        }
    },
    SHOWCREATETEAMPAGE{
        {
            this.command = new ShowCreateTeamPageCommand ();
        }
    },
    CREATETEAM{
        {
            this.command = new CreateTeamCommand ();
        }
    },
    GETLEAGUESBYCATEGORY{
        {
            this.command = new GetLeaguesByCategoryCommand ();
        }
    },
    SHOWCREATECOMPETITIONPAGE{
        {
            this.command = new ShowCreateCompetitionPageCommand ();
        }
    },
    CREATECOMPETITION{
        {
            this.command = new CreateCompetitionCommand ();
        }
    },
    GETOPPONENTSBYLEAGUEID{
        {
            this.command = new GetOpponentsByLeagueIdCommand ();
        }
    },
    SHOWCLOSECOMPETITIONPAGE{
        {
            this.command = new ShowCloseCompetitionPage ();
        }
    },
    CLOSECOMPETITION{
        {
            this.command = new CloseCompetitionCommand ();
        }
    },
    SHOWCOMPRATEPAGE{
        {
            this.command = new ShowCompetitionRatePageCommand ();
        }
    },
    CLOSECOMPETITIONRATE{
        {
            this.command = new CloseCompetitionRateCommand ();
        }
    },
    CHANGELOCALE{
        {
            this.command = new ChangeLocaleCommand ();
        }
    },
    CHOOSEPERSONALPAGE{
        {
            this.command = new ChoosePersonalPageCommand ();
        }
    },

    SHOWACTIVEBETTINGPAGE{
        {
            this.command = new ShowActiveBettingPageCommand ();
        }
    },
    SHOWRESULTBETTINGPAGE{
        {
            this.command = new ShowResultBettingPageCommand ();
        }
    },
    SHOWMODERATORPAGE{
        {
            this.command = new ShowModeratorPageCommand();
        }
    },
    CHOOSEMODERATORPAGE {
        {
            this.command = new ChooseModeratorPageCommand ();
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
