package by.training.lihodievski.final_project.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Opponent implements Entity{
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String nameOpponent;
    private League league;

    public Opponent() {

    }
    @Override
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOpponent() {
        return nameOpponent;
    }

    public void setNameOpponent(String nameOpponent) {
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
