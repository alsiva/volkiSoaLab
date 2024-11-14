package volki.soalab;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import volki.soalab.dto.dragon.ColorDto;
import volki.soalab.exceptions.IllegalParamException;


public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        // Инициализация не требуется, если просто проверяем существование в enum
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        // Если значение null, оно считается невалидным
        return value != null;
    }

    // Проверка, является ли значение допустимым в enum
    private static boolean isValidEnum(Enum<?> value) {
        return value != null;  // В данном случае всегда true, так как null уже исключается
    }
}