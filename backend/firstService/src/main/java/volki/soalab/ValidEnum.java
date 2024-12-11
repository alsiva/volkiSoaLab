package volki.soalab;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Кастомная аннотация для проверки цвета
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {
    String message() default "Invalid value for enum";  // Сообщение об ошибке
    Class<?>[] groups() default {};                    // Группы для валидации
    Class<? extends Payload>[] payload() default {};   // Дополнительная информация
}