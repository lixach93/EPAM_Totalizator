package by.training.lihodievski.final_project.bean;

public enum Rate implements Entity {

    TOTAL(1, "total"),
    TEAM(2, "team");

    private long id;
    private String value;

    Rate(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public long getId() {
        return id;
    }
}
