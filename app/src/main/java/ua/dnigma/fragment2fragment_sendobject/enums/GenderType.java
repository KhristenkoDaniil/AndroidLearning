package ua.dnigma.fragment2fragment_sendobject.enums;

/**
 * Created by Даниил on 15.12.2016.
 */

public enum GenderType {
    MALE("Male"), FEMALE("Female");
    private String value;

    GenderType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
