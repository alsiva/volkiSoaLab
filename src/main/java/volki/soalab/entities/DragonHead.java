package volki.soalab.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="dragon_head")
@Data
public class DragonHead {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="eyes_count")
    private long eyesCount;
}
