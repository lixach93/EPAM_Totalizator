package by.training.lihodievski.final_project.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class League implements Entity{
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String nameLeague;
    private Category category;

    public League() {

    }

    public League(String name, Category category) {
        this.nameLeague = name;
        this.category = category;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameLeague() {
        return nameLeague;
    }

    public void setNameLeague(String nameLeague) {
        this.nameLeague = nameLeague;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
