package by.training.lihodievski.final_project.command.factory;

import by.training.lihodievski.final_project.command.*;
import by.training.lihodievski.final_project.command.competition.*;
import by.training.lihodievski.final_project.command.league.AddLeagueCommand;
import by.training.lihodievski.final_project.command.league.AddLeaguePageCommand;
import by.training.lihodievski.final_project.command.league.GetLeaguesByCategoryCommand;
import by.training.lihodievski.final_project.command.locale.ChangeLocaleCommand;
import by.training.lihodievski.final_project.command.opponent.AddTeamCommand;
import by.training.lihodievski.final_project.command.opponent.GetOpponentsByLeagueIdCommand;
import by.training.lihodievski.final_project.command.opponent.ShowAddTeamPageCommand;
import by.training.lihodievski.final_project.command.user.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum CommandEnum {
    ADDCATEGORYPAGE {
        {
            this.command = new AddCategoryPageCommand ();
        }
    },
    ADDLEAGUE {
        {
            this.command = new AddLeagueCommand ();
        }
    },
    ADDLEAGUEPAGE {
        {
            this.command = new AddLeaguePageCommand ();
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
    MAKERATE {
        {
            this.command = new MakeRateCommand ();
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
    SHOWCOMPETITIONRATE {
        {
            this.command = new ShowCompetitionRateCommand ();
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
    SHOWADDTEAMPAGE{
        {
            this.command = new ShowAddTeamPageCommand ();
        }
    },
    ADDTEAM{
        {
            this.command = new AddTeamCommand ();
        }
    },
    GETLEAGUESBYCATEGORYID{
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
    };



    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

    public void initCommand(HttpServletRequest request, HttpServletResponse response) {
        this.command.init (request, response);
    }
    }
