package volki.soalab;

import org.springframework.stereotype.Component;
import volki.soalab.dto.ColorDto;
import volki.soalab.dto.CoordinatesDto;
import volki.soalab.dto.DragonDto;
import volki.soalab.dto.DragonHeadDto;
import volki.soalab.entities.Color;
import volki.soalab.entities.Coordinates;
import volki.soalab.entities.Dragon;
import volki.soalab.entities.DragonHead;

@Component
public class DragonConverter {
    public DragonDto toDragonDto(Dragon dragon) {
        if (dragon == null) {
            return null; // Обрабатываем случай, когда dragon равен null
        }


        return new DragonDto(
                dragon.getId(),
                dragon.getName(),
                toCoordinatesDto(dragon.getCoordinates()),
                dragon.getCreationDate(),
                dragon.getAge(),
                dragon.getWingspan(),
                dragon.isSpeaking(),
                toColorDto(dragon.getColor()),
                toDragonHeadDto(dragon.getHead())
        );
    }

    public CoordinatesDto toCoordinatesDto(Coordinates coordinates) {
        return new CoordinatesDto(
                coordinates.getX(),
                coordinates.getY()
        );
    }

    public ColorDto toColorDto(Color color) {
        return ColorDto.valueOf(color.getName());
    }

    public DragonHeadDto toDragonHeadDto(DragonHead dragonHead) {
        return new DragonHeadDto(
                dragonHead.getEyesCount()
        );
    }
}
