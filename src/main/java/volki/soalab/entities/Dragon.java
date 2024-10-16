package volki.soalab.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name="dragon")
@Data
public class Dragon {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(name="name",nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates; //Поле не может быть null

    @Column(name="creation_date", nullable = false, updatable = false)
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name="age")
    private Long age; //Значение поля должно быть больше 0, Поле может быть null

    @Column(name="wingspan")
    private Float wingspan; //Значение поля должно быть больше 0, Поле может быть null

    @Column(name="speaking")
    private boolean speaking;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color; //Поле не может быть null

    @ManyToOne
    @JoinColumn(name = "dragon_head_id", nullable = false)
    private DragonHead head;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}
