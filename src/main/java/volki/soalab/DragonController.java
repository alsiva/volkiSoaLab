package volki.soalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volki.soalab.dto.Dragon.DragonDto;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.dto.DragonCountDto;

import java.util.List;

@RestController
@RequestMapping("/dragons")
public class DragonController {

    private final DragonService dragonService;

    @Autowired
    public DragonController(DragonService dragonService) {
        this.dragonService = dragonService;
    }

    @GetMapping(produces = "application/xml")
    public List<DragonDtoWithId> getDragons(@RequestParam List<String> filter) {
        return dragonService.getDragons(filter);
    }

    @GetMapping(value = "/{id}", produces = "application/xml")
    public ResponseEntity<DragonDtoWithId> getDragonById(@PathVariable long id) {
        return dragonService.getDragonById(id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/xml")
    public ResponseEntity<DragonDtoWithId> deleteDragonById(@PathVariable long id) {
        return dragonService.deleteDragonById(id);
    }

    @PostMapping(produces = "application/xml", consumes = "application/xml")
    public DragonDtoWithId addDragon(@RequestBody DragonDto dragonDto) {
        return dragonService.addDragon(dragonDto);
    }

    @GetMapping(value = "/count", produces = "application/xml")
    public ResponseEntity<DragonCountDto> countDragonsByEyes(@RequestParam long eyesCount) {
        DragonCountDto dto = dragonService.getDragonCountByEyesCount(eyesCount);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/count/{color}", produces = "application/xml")
    public ResponseEntity<DragonCountDto> countDragonsByEyes(@PathVariable String color) {
        DragonCountDto dragonCountDto = dragonService.getDragonByColorGreaterThen(color);
        return ResponseEntity.ok(dragonCountDto);
    }

    @GetMapping(value = "/search/{substring}", produces = "application/xml")
    public ResponseEntity<List<DragonDtoWithId>> getDragonsBySubName(@PathVariable String substring) {
        List<DragonDtoWithId> dragonDtoWithIdList = dragonService.getDragonsWhereNameContains(substring);
        return ResponseEntity.ok(dragonDtoWithIdList);
    }


}
