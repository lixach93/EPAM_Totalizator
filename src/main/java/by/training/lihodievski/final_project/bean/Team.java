package by.training.lihodievski.final_project.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Team implements Entity{
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String nameTeam;
    private League league;

    public Team() {

    }
    @Override
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", nameTeam=" + nameTeam +
                ", league=" + league +
                '}';
    }
}
