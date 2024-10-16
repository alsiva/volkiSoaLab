package volki.soalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volki.soalab.dto.DragonDto;
import volki.soalab.entities.Dragon;
import java.util.List;


@Service
public class DragonService {

    private final DragonRepository dragonRepository;
    private final DragonConverter dragonConverter;

    @Autowired
    public DragonService(DragonRepository dragonRepository, DragonConverter dragonConverter) {
        this.dragonRepository = dragonRepository;
        this.dragonConverter = dragonConverter;
    }

    public List<DragonDto> getDragons() {
        return ((List<Dragon>) dragonRepository.findAll())
                .stream().map(dragonConverter::toDragonDto)
                .toList();
    }

}
