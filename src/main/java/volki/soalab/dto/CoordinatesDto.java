package volki.soalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CoordinatesDto {
    @Max(value = 411, message = "x must be lower or equal than 411")
    @NotNull(message = "x cannot be null")
    private Double x; //Максимальное значение поля: 411, Поле не может быть null
    @Min(value = -296, message = "y must be greater or equal than -296")
    private long y; //Значение поля должно быть больше -296
}
