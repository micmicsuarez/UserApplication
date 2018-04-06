package ph.com.masagana.type;

public enum ApiError {

    EMAIL_ADDRESS_EXISTS("Email address already exists"),
    EMAIL_ADDRESS_NOT_FOUND("Email address not found"),
    USERNAME_EXISTS_ALREADY("Username exists already");

    private String name;

    ApiError(String name) {
        this.name = name;
    }

    public String value() {
        return this.name;
    }
}
