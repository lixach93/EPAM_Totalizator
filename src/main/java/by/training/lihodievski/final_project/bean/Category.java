package by.training.lihodievski.final_project.bean;

public enum Category implements Entity {

    FOOTBALL(1,"football"),
    HOCKEY(2,"hockey"),
    BASKETBALL(3,"basketball");

    Category(long id, String categoryName) {
        this.categoryName = categoryName;
        this.id =id;
    }

    private long id;
    private String categoryName;


    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public long getId() {
        return id;
    }
}
