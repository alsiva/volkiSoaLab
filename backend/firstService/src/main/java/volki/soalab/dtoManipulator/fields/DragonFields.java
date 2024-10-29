package volki.soalab.dtoManipulator.fields;

import volki.soalab.exceptions.IllegalParamException;

public enum DragonFields {
    id,
    name,
    age,
    coordinates,
    creationDate,
    wingspan,
    speaking,
    color,
    eyesCount;

    public static DragonFields fromString(String s) {
        try {
            return DragonFields.valueOf(s.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalParamException(
                    String.format("Field (%s) doesn't exist", s.toLowerCase())
            );
        }
    }
}