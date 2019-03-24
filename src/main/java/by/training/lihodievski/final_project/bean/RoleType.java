package by.training.lihodievski.final_project.bean;

public enum  RoleType {

    ADMINISTRATOR("administrator"),
    MODERATOR("moderator"),
    USER("user");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}