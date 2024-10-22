package volki.soalab.dragonManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dragonManipulator.manipulationMachines.FilterMachine;
import volki.soalab.dragonManipulator.manipulationMachines.SortMachine;
import volki.soalab.dragonManipulator.paramsStringRepresenation.FilterAsString;
import volki.soalab.dragonManipulator.paramsStringRepresenation.SortAsString;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DragonListManipulator {

    private final FilterMachine filterMachine;
    private final SortMachine sortMachine;

    @Autowired
    public DragonListManipulator(FilterMachine filterMachine, SortMachine sortMachine) {
        this.filterMachine = filterMachine;
        this.sortMachine = sortMachine;
    }

    public List<DragonDtoWithId> filter(List<DragonDtoWithId> dragonDtoWithIdList, List<String> filterListAsString) {
        List<FilterAsString> filterList = filterListAsString.stream()
                .map(FilterAsString::new)
                .toList();

        return dragonDtoWithIdList.stream()
                .filter(dragonDto -> filterList.stream()
                        .allMatch(filterAsString -> filterMachine.matches(dragonDto, filterAsString)))
                .collect(Collectors.toList());
    }

    public List<DragonDtoWithId> page(List<DragonDtoWithId> dragonDtoWithIdList, String page) {

        if (page < 1 || pageSize < 1) {
            throw new IllegalParamException("Page number and size should be more than or equal 1");
        }

        return dragonDtoWithIdList.stream()
                .skip((page-1) * pageSize)
                .limit(page)
                .toList();
    }

    public List<DragonDtoWithId> sort(List<DragonDtoWithId> dragonDtoWithIdList, List<String> sortListAsString) {
        List<SortAsString> sortAsString = sortListAsString.stream().map(SortAsString::new).toList();
        return sortMachine.sort(dragonDtoWithIdList, sortAsString);
    }


}
