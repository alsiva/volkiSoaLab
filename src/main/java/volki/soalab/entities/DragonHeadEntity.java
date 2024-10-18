package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.dto.DragonHeadDto;

@Embeddable
@Data
@NoArgsConstructor
public class DragonHeadEntity {

    @Column(name = "eyes_count")
    private long eyesCount;

    public DragonHeadEntity(DragonHeadDto dragonHeadDto) {
        this.eyesCount = dragonHeadDto.getEyesCount();
    }
}
