package volki.soalab.dragonManipulator.paramsStringRepresenation;

import volki.soalab.exceptions.IllegalParamException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageAsString {
    private String number;
    private String size;

/*
    public FilterAsString(String filterAsString) {
        String filterRegex = "^([a-zA-Z_][a-zA-Z0-9_]*)-(eq|nq|gt|lt|gte|lte)-(.+)$";
        Pattern patter = Pattern.compile(filterRegex);


        Matcher matcher = patter.matcher(filterAsString);
        if (!matcher.matches()) {
            throw new IllegalParamException(
                    String.format("Filter (%s) doesn't match field-value-operator pattern", filterAsString)
            );
        }
        this.field = matcher.group(1);
        this.operator = matcher.group(2);
        this.value = matcher.group(3);
    }

 */
}
