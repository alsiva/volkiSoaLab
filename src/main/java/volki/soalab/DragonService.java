package volki.soalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volki.soalab.dto.DragonDto;
import volki.soalab.entities.Dragon;
import volki.soalab.filters.FilterMachine;

import java.util.List;


@Service
public class DragonService {

    private final DragonRepository dragonRepository;
    private final DragonConverter dragonConverter;
    private final FilterMachine filterMachine;
    @Autowired
    public DragonService(DragonRepository dragonRepository, DragonConverter dragonConverter, FilterMachine filterMachine) {
        this.dragonRepository = dragonRepository;
        this.dragonConverter = dragonConverter;
        this.filterMachine = filterMachine;
    }

    public List<DragonDto> getDragons(List<String> filter) {
        List<DragonDto> dragonDtoList = ((List<Dragon>) dragonRepository.findAll())
                .stream().map(dragonConverter::toDragonDto)
                .toList();

        return filterMachine.filter(dragonDtoList, filter);
    }

}
