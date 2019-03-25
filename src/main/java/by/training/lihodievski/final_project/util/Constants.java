package by.training.lihodievski.final_project.util;

public class Constants {

    public static final String PARAMETER_PAGE = "page";
    public static final String PARAMETER_REDIRECT = "redirect";
    public static final String PARAMETER_EVENT_ID = "eventId";
    public static final String PARAMETER_TEAM = "team";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_EMAIL = "email";

    public static final int PER_PAGE = 2;

    public static final String SESSION_ATTRIBUTE_USER_ID = "userId";
    public static final String SESSION_ATTRIBUTE_STATUS = "status";
    public static final String SESSION_ATTRIBUTE_ERROR = "errorMessage";
    public static final String SESSION_ATTRIBUTE_LOGIN = "userLogin";
    public static final String SESSION_ATTRIBUTE_ROLE = "userRole";

    public static final String REQUEST_ATTRIBUTE_PERMISSION = "permissionMessage";
    public static final String REQUEST_ATTRIBUTE_ACTIVE_ONE = "activeOne";
    public static final String REQUEST_ATTRIBUTE_ACTIVE_TWO = "activeTwo";
    public static final String REQUEST_ATTRIBUTE_ACTIVE_THREE = "activeThree";
    public static final String REQUEST_ATTRIBUTE_ACTIVE_FOUR = "activeFour";
    public static final String REQUEST_ATTRIBUTE_ACTIVE_FIVE = "activeFive";
    public static final String REQUEST_ATTRIBUTE_ACTION = "action";
    public static final String REQUEST_ATTRIBUTE_JSON = "json";
    public static final String REQUEST_ATTRIBUTE_BETS = "bets";
    public static final String REQUEST_ATTRIBUTE_USER = "user";
    public static final String REQUEST_ATTRIBUTE_USERS = "users";
    public static final String REQUEST_ATTRIBUTE_COUNT_PAGE = "countPage";
    public static final String REQUEST_ATTRIBUTE_SIZE = "size";

    public static final String RESPONSE_CONTENT_TYPE = "Content-type";

    public static final String FORWARD_PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    public static final String FORWARD_MODERATOR_PAGE = "/WEB-INF/view/moderator.jsp";
    public static final String FORWARD_ADMIN_PAGE = "/WEB-INF/view/admin.jsp";
    public static final String FORWARD_MAIN_PAGE = "/WEB-INF/view/main.jsp";
    public static final String FORWARD_ERROR_PAGE = "/WEB-INF/error/error500.jsp";
    public static final String FORWARD_EVENT_PAGE = "/WEB-INF/view/event.jsp";
    public static final String FORWARD_JSON_PAGE = "/WEB-INF/ajax/json.jsp";
    public static final String FORWARD_LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    public static final String FORWARD_REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";

    public static final String ERROR_INPUT_LOGIN_NOT_VALID = "error.login.notValid";
    public static final String ERROR_LOGIN_USED = "error.login.used";
    public static final String ERROR_EMAIL_USED= "error.email.used";
    public static final String ERROR_INPUT_USER_IS_NOT_EXISTS = "error.user.null";
    public static final String ERROR_INPUT_PASSWORD_NOT_VALID = "error.password.notValid";
    public static final String ERROR_INPUT_PASSWORD_IS_NOT_EQUALS = "error.password.isNotEquals";
    public static final String ERROR_MESSAGE = "error.message";
    public static final String ERROR_NO_MONEY = "error.money";
    public static final String ERROR_PERMISSION_INFO = "error.permission";

    public static final String STATUS_SUCCESS = "info.successful";
    public static final String STATUS_UN_SUCCESS = "info.unsuccessful";

    public static final String LEAGUE = "league";
    public static final String BETTING = "bet";
    public static final String PERSONAL = "personal";
    public static final String TEAM = "team";
    public static final String ACTIVE = "active";
    public static final String JSON = "json";
    public static final String EVENT = "event";
    public static final String EVENTS = "events";
    public static final String CATEGORIES = "categories";
    public static final String COMPETITIONS = "competitions";

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_PROPERTIES = "database";
    public static final String DB_URL = "db.url";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_USER = "db.user";
    public static final String DB_POOL = "db.poolSize";

    public static final String TEAM_FIRST_NAME = "t1.name";
    public static final String TEAM_FIRST_ID = "team_first_id";
    public static final String TEAM_SECOND_NAME = "t2.name";
    public static final String TEAM_SECOND_ID = "team_second_id";
    public static final String TEAM_FIRST_RESULT = "team_first_result";
    public static final String TEAM_SECOND_RESULT = "team_second_result";
    public static final String TEAM_FIRST_SCORE = "team_first_score";
    public static final String TEAM_SECOND_SCORE = "team_second_score";
    public static final String WINNER_RESULT = "winner_result";
    public static final String WINNER_ID = "winner_id";
    public static final String WIN_MONEY = "win_money";
    public static final String RATE_TYPE_NAME = "rate_type.name";
    public static final String USER_ID = "user_id";
    public static final String USER_ID_ALIAS = "userId";
    public static final String USER_LOGIN = "login";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MONEY = "money";
    public static final String USER_ROLE = "role";
    public static final String BET_ID = "bet_id";
    public static final String BET_MONEY = "betMoney";
    public static final String COMPETITION_STATUS = "competition.status";
    public static final String COMPETITION_ID = "competition_id";
    public static final String STATUS = "status";
    public static final String STATUS_NEW = "new";
    public static final String STATUS_FINISHED = "finished";
    public static final String BET_ID_ALIAS = "betId";
    public static final String COMPETITION_STATUS_ALIAS = "c.status";
    public static final String CATEGORY_NAME = "category.name";
    public static final String LEAGUE_NAME = "name";
    public static final String LEAGUE_ID = "league_id";
    public static final String TEAM_NAME = "name";
    public static final String TEAM_ID = "team_id";
    public static final String EVENT_PAYMENT = "e.payment";
    public static final String EVENT_PERCENT = "e.percent";
    public static final String EVENT_WIN_PERCENT = "e.win_percent";
    public static final String EVENT_TEAM_SECOND_NAME = "team_second_name";
    public static final String EVENT_TEAM_FIRST_NAME = "team_first_name";
    public static final String EVENT_ID = "e.event_id";
    public static final String EVENT_COMPETITION_ID = "e.competition_id";
    public static final String COUNT_EVENT = "countEvent";
    public static final String EVENT_CATEGORY_FIRST_NAME = "category_first_name";
    public static final String EVENT_CATEGORY_SECOND_NAME = "category_second_name";
    public static final String EVENT_LEAGUE_FIRST_NAME = "league_first_name";
    public static final String EVENT_LEAGUE_SECOND_NAME = "league_second_name";
    public static final String EVENT_WINNER = "c.winner";
    public static final String EVENT_STATUS = "c.status";
    public static final String RATE_VALUE = "rate_value";
    public static final String EVENT_TEAM_FIRST_RESULT = "c.team_first_result";
    public static final String EVENT_TEAM_SECOND_RESULT = "c.team_second_result";






}
