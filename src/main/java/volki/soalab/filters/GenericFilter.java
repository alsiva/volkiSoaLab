package volki.soalab.filters;

import org.springframework.stereotype.Component;
import volki.soalab.dto.DragonDto;

@Component
public class GenericFilter {

    private <T extends Comparable<T>> boolean genericMatcher(T a, String operator, T b) {
        return switch (operator) {
            case "eq" -> a.equals(b);
            case "nq" -> !a.equals(b);
            case "gt" -> a.compareTo(b) > 0;
            case "lt" -> a.compareTo(b) < 0;
            case "ge" -> a.compareTo(b) >= 0;
            case "le" -> a.compareTo(b) <= 0;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }


    public boolean matches(DragonDto dragonDto, FilterAsString filterAsString) {

        String field = filterAsString.getField();
        String operator = filterAsString.getOperator();
        String value = filterAsString.getValue();
        return switch (field) {
            case "id":
                yield genericMatcher(
                        dragonDto.getId(),
                        operator,
                        Long.parseLong(value)
                );
            case "name":
                yield genericMatcher(
                        dragonDto.getName(),
                        operator,
                        value
                );
            case "age":
                yield genericMatcher(
                        dragonDto.getAge(),
                        operator,
                        Long.parseLong(value)
                );
            default:
                throw new IllegalArgumentException("Unsupported field: " + field);
        };
    }


}
