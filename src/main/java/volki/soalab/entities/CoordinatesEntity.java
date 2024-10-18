package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.dto.CoordinatesDto;

@Embeddable
@Data
@NoArgsConstructor
public class CoordinatesEntity {
    @Column(name = "coordinate_x")
    private Double x; //Максимальное значение поля: 411, Поле не может быть null
    @Column(name = "coordinate_y")
    private long y; //Значение поля должно быть больше -296

    public CoordinatesEntity(CoordinatesDto coordinatesDto) {
        this.x = coordinatesDto.getX();
        this.y = coordinatesDto.getY();
    }
}
