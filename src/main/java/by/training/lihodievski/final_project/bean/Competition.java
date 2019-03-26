package by.training.lihodievski.final_project.bean;

public class Competition implements Entity {

    private long id;
    private Team firstTeam;
    private Team secondTeam;
    private String status;
    private int firstTeamResult;
    private int secondTeamResult;
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

    public int getFirstTeamResult() {
        return firstTeamResult;
    }

    public void setFirstTeamResult(int firstTeamResult) {
        this.firstTeamResult = firstTeamResult;
    }

    public int getSecondTeamResult() {
        return secondTeamResult;
    }

    public void setSecondTeamResult(int secondTeamResult) {
        this.secondTeamResult = secondTeamResult;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass () != o.getClass ()) {
            return false;
        }

        Competition that = (Competition) o;

        if (id != that.id) {
            return false;
        }
        if (firstTeamResult != that.firstTeamResult) {
            return false;
        }
        if (secondTeamResult != that.secondTeamResult) {
            return false;
        }
        if (winner != that.winner) {
            return false;
        }
        if (firstTeam != null ? !firstTeam.equals (that.firstTeam) : that.firstTeam != null) {
            return false;
        }
        if (secondTeam != null ? !secondTeam.equals (that.secondTeam) : that.secondTeam != null) {
            return false;
        }
        return status != null ? status.equals (that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstTeam.hashCode ();
        result = 31 * result + secondTeam.hashCode ();
        result = 31 * result + status.hashCode ();
        result = 31 * result + firstTeamResult;
        result = 31 * result + secondTeamResult;
        result = 31 * result + winner;
        return result;
    }
}
