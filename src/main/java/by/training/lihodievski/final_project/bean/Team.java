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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (nameTeam != null ? !nameTeam.equals (team.nameTeam) : team.nameTeam != null) return false;
        return league != null ? league.equals (team.league) : team.league == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameTeam != null ? nameTeam.hashCode () : 0);
        result = 31 * result + (league != null ? league.hashCode () : 0);
        return result;
    }
}
