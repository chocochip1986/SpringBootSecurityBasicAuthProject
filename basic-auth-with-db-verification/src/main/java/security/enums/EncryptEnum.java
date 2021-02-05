package security.enums;

public enum EncryptEnum {
    BCRYPT("{bcrypt}");

    private String tag;

    EncryptEnum(String tag) {
        this.tag = tag;
    }

    public String tag() {
        return this.tag;
    }
}
