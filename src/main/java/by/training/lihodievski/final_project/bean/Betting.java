package by.training.lihodievski.final_project.bean;

public class Betting implements Entity {

    private long id;
    private User user;
    private Event event;
    private int winner;
    private int opponentFirstScore;
    private int opponentSecondScore;
    private double money;
    private double winMoney;

    public Betting() {
    }

    public Betting(User user, Event event, int winner, double money) {
        this.user = user;
        this.event = event;
        this.winner = winner;
        this.money = money;
    }

    public Betting(User user, Event event, int opponentFirstScore, int opponentSecondScore, double money) {
        this.user = user;
        this.event = event;
        this.opponentFirstScore = opponentFirstScore;
        this.opponentSecondScore = opponentSecondScore;
        this.money = money;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getOpponentFirstScore() {
        return opponentFirstScore;
    }

    public void setOpponentFirstScore(int opponentFirstScore) {
        this.opponentFirstScore = opponentFirstScore;
    }

    public int getOpponentSecondScore() {
        return opponentSecondScore;
    }

    public void setOpponentSecondScore(int opponentSecondScore) {
        this.opponentSecondScore = opponentSecondScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(double winMoney) {
        this.winMoney = winMoney;
    }
}
