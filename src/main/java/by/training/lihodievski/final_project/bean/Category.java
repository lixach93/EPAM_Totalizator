package by.training.lihodievski.final_project.bean;

public enum Category implements Entity {

    FOOTBALL(1,"football"),
    HOCKEY(2,"hockey"),
    BASKETBALL(3,"basketball");

    Category(long id, String nameCategory) {
        this.nameCategory = nameCategory;
        this.id =id;
    }

    private long id;
    private String nameCategory;


    public String getNameCategory() {
        return nameCategory;
    }

    @Override
    public long getId() {
        return id;
    }
}
