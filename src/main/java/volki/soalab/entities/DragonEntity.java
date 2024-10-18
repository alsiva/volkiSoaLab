package volki.soalab.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import volki.soalab.dto.Dragon.DragonDto;

import java.time.LocalDateTime;

@Entity(name="dragon")
@Data
@NoArgsConstructor
public class DragonEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(name="name",nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Embedded
    private CoordinatesEntity coordinatesEntity; //Поле не может быть null

    @Column(name="creation_date", nullable = false, updatable = false)
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name="age")
    private Long age; //Значение поля должно быть больше 0, Поле может быть null

    @Column(name="wingspan")
    private Float wingspan; //Значение поля должно быть больше 0, Поле может быть null

    @Column(name="speaking")
    private boolean speaking;

    @Embedded
    private ColorEntity colorEntity; //Поле не может быть null

    @Embedded
    private DragonHeadEntity head;

    public DragonEntity(DragonDto dragonDto) {
        this.name = dragonDto.getName();
        this.coordinatesEntity = new CoordinatesEntity(dragonDto.getCoordinates());
        this.creationDate = dragonDto.getCreationDate();
        this.age = dragonDto.getAge();
        this.wingspan = dragonDto.getWingspan();
        this.speaking = dragonDto.isSpeaking();
        this.colorEntity = new ColorEntity(dragonDto.getColor());
        this.head = new DragonHeadEntity(dragonDto.getHead());
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}
