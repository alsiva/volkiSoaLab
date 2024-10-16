package volki.soalab.filters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class GenericFilter {
    public <T extends Comparable<T>> boolean matches(T field, String operator, T value) {
        return switch (operator) {
            case "eq" -> field.equals(value);
            case "nq" -> !field.equals(value);
            case "gt" -> field.compareTo(value) > 0;
            case "lt" -> field.compareTo(value) < 0;
            case "ge" -> field.compareTo(value) >= 0;
            case "le" -> field.compareTo(value) <= 0;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator); //TODO вернуть exception
        };
    }


}
