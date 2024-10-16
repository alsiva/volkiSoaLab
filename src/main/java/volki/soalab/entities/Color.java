package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="color")
@Data
public class Color {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Column(name="name")
    private String name;

}
