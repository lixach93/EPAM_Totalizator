package by.training.lihodievski.final_project.bean;

public class Competition implements Entity {

    private long id;
    private Team firstTeam;
    private Team secondTeam;
    private String status;
    private int firstOpponentResult;
    private int secondOpponentResult;
    private int winner;
    public Competition() {
        firstTeam = new Team ();
        secondTeam = new Team ();
    }

    public Competition(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
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
                ", firstTeam=" + firstTeam +
                ", secondTeam=" + secondTeam +
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
