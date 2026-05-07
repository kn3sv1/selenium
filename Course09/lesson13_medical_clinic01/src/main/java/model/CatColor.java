package model;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum CatColor {
    WHITE,
    BLACK_AND_WHITE,
    ORANGE,
    GREY,
    BLACK,
    BEIGE,
    BROWN,
    MULTICOLORED;

    @JsonCreator
    public static CatColor fromString(String value) {
        for (CatColor color : CatColor.values()) {
            if (color.name().equalsIgnoreCase(
                    value.replace(" ", "_")
            )) {
                return color;
            }
        }

        throw new IllegalArgumentException("Invalid color: " + value);
    }
}


