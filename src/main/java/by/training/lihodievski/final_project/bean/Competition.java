package by.training.lihodievski.final_project.bean;

public class Competition implements Entity {

    private long id;
    private Opponent firstOpponent;
    private Opponent secondOpponent;
    private String status;
    private int firstOpponentResult;
    private int secondOpponentResult;
    private int winner;
    public Competition() {
        firstOpponent = new Opponent ();
        secondOpponent = new Opponent ();
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Opponent getFirstOpponent() {
        return firstOpponent;
    }

    public void setFirstOpponent(Opponent firstOpponent) {
        this.firstOpponent = firstOpponent;
    }

    public Opponent getSecondOpponent() {
        return secondOpponent;
    }

    public void setSecondOpponent(Opponent secondOpponent) {
        this.secondOpponent = secondOpponent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFirstOpponentResult() {
        return firstOpponentResult;
    }

    public void setFirstOpponentResult(int firstOpponentResult) {
        this.firstOpponentResult = firstOpponentResult;
    }

    public int getSecondOpponentResult() {
        return secondOpponentResult;
    }

    public void setSecondOpponentResult(int secondOpponentResult) {
        this.secondOpponentResult = secondOpponentResult;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + getId () +
                ", firstOpponent=" + firstOpponent +
                ", secondOpponent=" + secondOpponent +
                ", status='" + status + '\'' +
                ", firstOpponentResult=" + firstOpponentResult +
                ", secondOpponentResult=" + secondOpponentResult +
                '}';
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}
