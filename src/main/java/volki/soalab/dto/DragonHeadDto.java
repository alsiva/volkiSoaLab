package volki.soalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.entities.DragonHeadEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "head")
public class DragonHeadDto {
    @XmlElement(name = "eyesCount")
    private long eyesCount;

    public DragonHeadDto(DragonHeadEntity dragonHeadEntity) {
        this.eyesCount = dragonHeadEntity.getEyesCount();
    }
}
