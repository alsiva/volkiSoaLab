package volki.soalab.dto.Dragon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import volki.soalab.entities.DragonEntity;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Setter
@XmlRootElement(name = "dragon")
@NoArgsConstructor
public class DragonDtoWithId extends DragonDto {

    // id должно быть больше 0, генерируется автоматически
    @Min(value = 1, message = "Id must be greater than 0")
    @XmlElement(name = "id")
    private long id;

    public DragonDtoWithId(DragonEntity dragonEntity) {
        super(dragonEntity);
        this.id = dragonEntity.getId();
    }


}
