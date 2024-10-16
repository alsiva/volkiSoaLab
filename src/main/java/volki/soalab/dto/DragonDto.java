package volki.soalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data // Lombok генерирует геттеры, сеттеры, toString, equals и hashCode
@AllArgsConstructor
public class DragonDto {

    // id должно быть больше 0, генерируется автоматически
    @Min(value = 1, message = "Id must be greater than 0")
    private long id;

    // name не может быть null и пустым
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    // coordinates не может быть null
    @NotNull(message = "Coordinates cannot be null")
    private CoordinatesDto coordinates;

    // creationDate генерируется автоматически, не может быть null
    @NotNull(message = "Creation date cannot be null")
    private LocalDateTime creationDate;

    // age должно быть больше 0, может быть null
    @Min(value = 1, message = "Age must be greater than 0")
    private Long age;

    // wingspan должно быть больше 0, может быть null
    @Min(value = 1, message = "Wingspan must be greater than 0")
    private Float wingspan;

    // speaking (без дополнительных ограничений)
    private boolean speaking;

    // color не может быть null
    @NotNull(message = "Color cannot be null")
    private ColorDto color;

    // head (без дополнительных ограничений)
    private DragonHeadDto head;
}
