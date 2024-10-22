package volki.soalab.dragonManipulator.mapper.fieldMapper;

import org.springframework.stereotype.Component;
import volki.soalab.exceptions.IllegalParamException;

@Component
public class StringMapper implements Mapper<String>{
    @Override
    public Comparable<?> map(DragonFields dragonFields, String value) {
        switch (dragonFields) {
            case id, age -> {
                try {
                    return Long.parseLong(value);
                } catch (NumberFormatException numberFormatException) {
                    throw new IllegalParamException(
                            String.format("(%s) should be Long", value)
                    );
                }
            }
            case name -> {
                return value;
            }
            default -> throw new IllegalParamException(
                    String.format("Field (%s) is not supported", value)
            );
        }
    }
}
