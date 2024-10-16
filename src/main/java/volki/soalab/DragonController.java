package volki.soalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import volki.soalab.dto.DragonDto;

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
    public List<DragonDto> getDragons() {
        return dragonService.getDragons();
    }
}
