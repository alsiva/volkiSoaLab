package volki.soalab.dragonManipulator.manipulationMachines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dragonManipulator.mapper.GenericComparablePair;
import volki.soalab.dragonManipulator.mapper.ParamsMapper;
import volki.soalab.dragonManipulator.paramsStringRepresenation.SortAsString;
import volki.soalab.dto.Dragon.DragonDtoWithId;

import java.util.List;

@Component
public class SortMachine {

    private final ParamsMapper paramsMapper;

    @Autowired
    public SortMachine(ParamsMapper paramsMapper) {
        this.paramsMapper = paramsMapper;
    }
    public List<DragonDtoWithId> sort(List<DragonDtoWithId> dragonDtoWithIdList, List<SortAsString> sortAsStringList) {
        return dragonDtoWithIdList.stream().sorted(
                ((a, b) -> compare(a, b, sortAsStringList))
        ).toList();
    }

    private <T extends Comparable<T>> int compare(DragonDtoWithId a, DragonDtoWithId b, List<SortAsString> sortAsStringList) {
        for (SortAsString sortAsString: sortAsStringList) {
            GenericComparablePair<?> sortComparable = paramsMapper.genericSort(a, b, sortAsString.getField());

            int comparasionResult = sortComparable.compare();

            if (comparasionResult != 0) {
                return sortAsString.getAsc() ? comparasionResult : -comparasionResult;
            }
        }
        return 0;
    }

}
