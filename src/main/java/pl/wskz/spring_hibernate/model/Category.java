package pl.wskz.spring_hibernate.model;

public enum Category {
    IT("it"),
    PROGRAMMING("programowanie"),
    TESTING("testowanie"),
    DEV_OPS("administracja");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
