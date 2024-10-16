package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="coordinates")
@Data
public class Coordinates {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="x")
    private Double x; //Максимальное значение поля: 411, Поле не может быть null

    @Column(name="y")
    private long y; //Значение поля должно быть больше -296
}
