package by.training.lihodievski.final_project.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class League implements Entity{

    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String leagueName;
    private Category category;

    public League() {

    }

    public League(long id) {
        this.id = id;
    }

    public League(String leagueName, Category category) {
        this.leagueName = leagueName;
        this.category = category;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass () != o.getClass ()){
            return false;
        }
        League league = (League) o;

        if (id != league.id) {
            return false;
        }
        if (leagueName != null ? !leagueName.equals (league.leagueName) : league.leagueName != null) {
            return false;
        }
        return category == league.category;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (leagueName != null ? leagueName.hashCode () : 0);
        result = 31 * result + (category != null ? category.hashCode () : 0);
        return result;
    }
}
