package by.training.lihodievski.final_project.bean;

public class Bet implements Entity {

    private long id;
    private User user;
    private Event event;
    private int winner;
    private int opponentFirstScore;
    private int opponentSecondScore;
    private double money;
    private double winMoney;

    public Bet() {
    }

    public Bet(User user, Event event, int winner, double money) {
        this.user = user;
        this.event = event;
        this.winner = winner;
        this.money = money;
    }

    public Bet(User user, Event event, int opponentFirstScore, int opponentSecondScore, double money) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass () != o.getClass ()) {
            return false;
        }

        Bet bet = (Bet) o;

        if (id != bet.id) {
            return false;
        }
        if (winner != bet.winner) {
            return false;
        }
        if (opponentFirstScore != bet.opponentFirstScore) {
            return false;
        }
        if (opponentSecondScore != bet.opponentSecondScore) {
            return false;
        }
        if (Double.compare (bet.money, money) != 0){
            return false;
        }
        if (Double.compare (bet.winMoney, winMoney) != 0) {
            return false;
        }
        if (!user.equals (bet.user)) {
            return false;
        }
        return event.equals (bet.event);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode ();
        result = 31 * result + event.hashCode ();
        result = 31 * result + winner;
        result = 31 * result + opponentFirstScore;
        result = 31 * result + opponentSecondScore;
        temp = Double.doubleToLongBits (money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits (winMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
