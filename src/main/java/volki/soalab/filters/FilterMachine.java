package volki.soalab.filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dto.DragonDto;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterMachine {

    private final GenericFilter genericFilter;

    @Autowired
    public FilterMachine(GenericFilter genericFilter) {
        this.genericFilter = genericFilter;
    }

    public List<DragonDto> filter(List<DragonDto> dragonDtoList, List<String> filterListAsString) {
        List<FilterAsString> filterList = filterListAsString.stream()
                .map(FilterAsString::new)
                .toList();

        return dragonDtoList.stream()
                .filter(dragonDto -> filterList.stream()
                        .allMatch(filterAsString -> genericFilter.matches(dragonDto, filterAsString)))
                .collect(Collectors.toList());
    }
}
