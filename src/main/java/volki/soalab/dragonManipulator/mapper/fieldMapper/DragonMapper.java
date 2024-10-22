package volki.soalab.dragonManipulator.mapper.fieldMapper;

import org.springframework.stereotype.Component;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

import java.util.Objects;

@Component
public class DragonMapper implements Mapper<DragonDtoWithId>{
    @Override
    public Comparable<?> map(DragonFields dragonFields, DragonDtoWithId dragonDtoWithId) {
        return switch (dragonFields) {
            case id -> dragonDtoWithId.getId();
            case name -> dragonDtoWithId.getName();
            case age -> {
                Long age = dragonDtoWithId.getAge();
                yield Objects.requireNonNullElse(age, 0L);
            }
            default -> throw new IllegalParamException(
                    String.format("Field (%s) is not implemented", dragonFields)
            );
        };
    }
}
