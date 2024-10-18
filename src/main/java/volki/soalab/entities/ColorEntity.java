package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.dto.ColorDto;

@Embeddable
@Data
@NoArgsConstructor
public class ColorEntity {
    @Column(name = "color")
    private String name;
    public ColorEntity(ColorDto colorDto) {
        this.name = colorDto.name().toUpperCase();
    }

}
