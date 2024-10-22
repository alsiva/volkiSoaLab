package volki.soalab.dragonManipulator.manipulationMachines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dragonManipulator.mapper.ParamMapper;
import volki.soalab.dragonManipulator.paramsStringRepresenation.FilterAsString;
import volki.soalab.dragonManipulator.mapper.GenericComparablePair;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

@Component
public class FilterMachine {

    private final ParamMapper paramsMapper;

    @Autowired
    public FilterMachine(ParamMapper paramsMapper) {
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
        String operator = filterAsString.getOperator();
        GenericComparablePair<?> genericFieldValue = paramsMapper.FilterMapper(dragonDtoWithId, filterAsString);
        return genericMatcher(genericFieldValue, operator);
    }

}
