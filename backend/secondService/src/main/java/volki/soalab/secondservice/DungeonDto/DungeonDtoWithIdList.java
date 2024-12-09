package volki.soalab.secondservice.DungeonDto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "dungeonList")
@XmlAccessorType(XmlAccessType.FIELD)
public class DungeonDtoWithIdList {
    @XmlElement(name = "dungeon")
    private List<DungeonDto> dungeonDtoList;
}
