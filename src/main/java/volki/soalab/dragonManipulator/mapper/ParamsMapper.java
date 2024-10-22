package volki.soalab.dragonManipulator.mapper;

import org.springframework.stereotype.Component;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

@Component
public class ParamsMapper {

    public GenericComparablePair<?> genericFieldValue(DragonDtoWithId dragonDtoWithId, String fieldName, String value) {
        return  map(dragonDtoWithId, fieldName, value);
    }

    public GenericComparablePair<?> genericSort(DragonDtoWithId firstDragon, DragonDtoWithId secondDragon, String fieldName) {
        return map(firstDragon, fieldName, secondDragon);
    }


    private <T> GenericComparablePair<?>  map(
            DragonDtoWithId firstDragon,
            String fieldName,
            T holder
    ) {



        return switch (fieldName) {
            case "id" -> {
                if (holder instanceof String) {
                    long parsedValue;
                    try {
                        parsedValue = Long.parseLong((String) holder);
                    } catch (NumberFormatException e) {
                        throw new IllegalParamException(
                                String.format("Value (%s) isn't Long", holder)
                        );
                    }
                    yield new GenericComparablePair<>(firstDragon.getId(), parsedValue);
                }
                yield new GenericComparablePair<>(firstDragon.getId(), secondDragon.getId());
            }
            case "name" -> {
                if (value != null) {
                    yield new GenericComparablePair<>(firstDragon.getName(), value);
                }
                yield new GenericComparablePair<>(firstDragon.getName(), secondDragon.getName());
            }
            default -> throw new IllegalArgumentException("Unsupported field type: " + fieldName);
        };
    }
}
