package volki.soalab.dragonManipulator.manipulationMachines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dragonManipulator.paramsStringRepresenation.FilterAsString;
import volki.soalab.dragonManipulator.mapper.GenericComparablePair;
import volki.soalab.dragonManipulator.mapper.ParamsMapper;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

@Component
public class FilterMachine {

    private final ParamsMapper paramsMapper;

    @Autowired
    public FilterMachine(ParamsMapper paramsMapper) {
        this.paramsMapper = paramsMapper;
    }

    private <T extends Comparable<T>> boolean genericMatcher(GenericComparablePair<T> genericFieldValue, String operator) {

        T a = genericFieldValue.getFirst();
        T b = genericFieldValue.getSecond();

        if (a == null || b == null) {
            return a != null;
        }

        return switch (operator) {
            case "eq" -> a.equals(b);
            case "nq" -> !a.equals(b);
            case "gt" -> a.compareTo(b) > 0;
            case "lt" -> a.compareTo(b) < 0;
            case "ge" -> a.compareTo(b) >= 0;
            case "le" -> a.compareTo(b) <= 0;
            default -> throw new IllegalParamException("Unsupported operator: " + operator);
        };
    }


    public boolean matches(DragonDtoWithId dragonDtoWithId, FilterAsString filterAsString) {

        String field = filterAsString.getField();
        String operator = filterAsString.getOperator();
        String value = filterAsString.getValue();

        GenericComparablePair<?> genericFieldValue = paramsMapper.genericFieldValue(dragonDtoWithId, field, value);
        return genericMatcher(genericFieldValue, operator);
    }

}
