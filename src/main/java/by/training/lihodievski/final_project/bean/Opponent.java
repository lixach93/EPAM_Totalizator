package by.training.lihodievski.final_project.bean;

public class Opponent {

    private Long id;
    private int nameOpponent;
    private League league;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNameOpponent() {
        return nameOpponent;
    }

    public void setNameOpponent(int nameOpponent) {
        this.nameOpponent = nameOpponent;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "id=" + id +
                ", nameOpponent=" + nameOpponent +
                ", league=" + league +
                '}';
    }
}
