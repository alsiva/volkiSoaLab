package volki.soalab.dto.Dragon;

import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.dto.ColorDto;
import volki.soalab.dto.CoordinatesDto;
import volki.soalab.dto.DragonHeadDto;
import volki.soalab.entities.DragonEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "dragon")
@NoArgsConstructor
public class DragonDto {
    // name не может быть null и пустым
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @XmlElement(name = "name")
    private String name;

    // coordinates не может быть null
    @NotNull(message = "Coordinates cannot be null")
    @XmlElement(name = "coordinates")
    private CoordinatesDto coordinates;

    // creationDate генерируется автоматически, не может быть null
    @NotNull(message = "Creation date cannot be null")
    @XmlElement(name = "creationDate")
    private LocalDateTime creationDate;

    // age должно быть больше 0, может быть null
    @Min(value = 1, message = "Age must be greater than 0")
    @XmlElement(name = "age")
    private Long age;

    // wingspan должно быть больше 0, может быть null
    @Min(value = 1, message = "Wingspan must be greater than 0")
    @XmlElement(name = "wingspan")
    private Float wingspan;

    // speaking (без дополнительных ограничений)
    @XmlElement(name = "speaking")
    private boolean speaking;

    // color не может быть null
    @NotNull(message = "Color cannot be null")
    @XmlElement(name = "color")
    private ColorDto color;

    // head (без дополнительных ограничений)
    @XmlElement(name = "head")
    private DragonHeadDto head;

    public DragonDto(DragonEntity dragonEntity) {
        this.name = dragonEntity.getName();
        this.coordinates = new CoordinatesDto(dragonEntity.getCoordinatesEntity());
        this.creationDate = dragonEntity.getCreationDate();
        this.age = dragonEntity.getAge();
        this.wingspan = dragonEntity.getWingspan();
        this.speaking = dragonEntity.isSpeaking();
        this.color = ColorDto.fromEntity(dragonEntity.getColorEntity());
        this.head = new DragonHeadDto(dragonEntity.getHead());
    }

}
