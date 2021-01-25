package pl.wskz.spring_hibernate.model;

public enum SortParams {
    REGISTRATON_TIME("registrationTime"),
    EMAIL("email"),
    STATUS("status");

    private final String filedName;

    SortParams(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledName() {
        return filedName;
    }
}
