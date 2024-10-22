package volki.soalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volki.soalab.dto.ColorDto;
import volki.soalab.dto.Dragon.DragonDto;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.dto.DragonCountDto;
import volki.soalab.entities.DragonEntity;
import volki.soalab.dragonManipulator.DragonListManipulator;

import java.util.List;


@Service
public class DragonService {

    private final DragonRepository dragonRepository;
    private final DragonListManipulator dragonListManipulator;

    @Autowired
    public DragonService(DragonRepository dragonRepository, DragonListManipulator dragonListManipulator) {
        this.dragonRepository = dragonRepository;
        this.dragonListManipulator = dragonListManipulator;
    }

    public List<DragonDtoWithId> getDragons(List<String> filter, List<String> sort, List<String> page) {
        List<DragonDtoWithId> dragonDtoWithIdList = ((List<DragonEntity>) dragonRepository.findAll())
                .stream().map(DragonDtoWithId::new)
                .toList();

        if (filter != null && !filter.isEmpty()) {
            dragonDtoWithIdList = dragonListManipulator.filter(dragonDtoWithIdList, filter);
        }

        if (sort != null && !sort.isEmpty()) {
            dragonDtoWithIdList = dragonListManipulator.sort(dragonDtoWithIdList, sort);
        }

        if (page != null && !page.isEmpty()) {
            dragonDtoWithIdList = dragonListManipulator.page(dragonDtoWithIdList, page);
        }

        return dragonDtoWithIdList;
    }

    public DragonDtoWithId addDragon(DragonDto dragonDto) {
        return new DragonDtoWithId(dragonRepository.save(new DragonEntity(dragonDto)));
    }

    public ResponseEntity<DragonDtoWithId> getDragonById(long id) {
        return dragonRepository.findById(id)
                .map(dragon -> new ResponseEntity<>(new DragonDtoWithId(dragon), HttpStatus.OK))  // Если дракон найден
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Если дракон не найден

    }

    public ResponseEntity<DragonDtoWithId> deleteDragonById(long id) {
        return dragonRepository.findById(id)
                .map(dragon -> {
                    DragonDtoWithId deletedDragon = new DragonDtoWithId(dragon);  // Создаем DTO удаленного дракона
                    dragonRepository.deleteById(id);  // Удаляем дракона
                    return new ResponseEntity<>(deletedDragon, HttpStatus.OK);  // Возвращаем удаленного дракона
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Если дракон не найден

    }

    public DragonCountDto getDragonCountByEyesCount(long eyesCount) {
        return new DragonCountDto(
                ((List<DragonEntity>) dragonRepository.findAll())
                        .stream().filter(dragonEntity -> dragonEntity.getHead().getEyesCount() == eyesCount)
                        .count()
        );
    }

    public DragonCountDto getDragonByColorGreaterThen(String color) {
        return new DragonCountDto(
                ((List<DragonEntity>) dragonRepository.findAll())
                        .stream().map(DragonEntity::getColorEntity)
                        .map(ColorDto::fromEntity)
                        .filter(colorDto -> colorDto.compareTo(ColorDto.valueOf(color.toUpperCase())) >= 0)
                        .count()
        );
    }

    public List<DragonDtoWithId> getDragonsWhereNameContains(String substring) {
        return ((List<DragonEntity>) dragonRepository.findAll())
                        .stream().map(DragonDtoWithId::new)
                        .filter(dragonDtoWithId -> dragonDtoWithId.getName().contains(substring))
                        .toList();
    }
}
