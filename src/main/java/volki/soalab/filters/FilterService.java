package volki.soalab.filters;

import com.sun.nio.sctp.PeerAddressChangeNotification;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volki.soalab.dto.DragonDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FilterService {

    private final GenericFilter genericFilter;

    @Autowired
    public FilterService(GenericFilter genericFilter) {
        this.genericFilter = genericFilter;
    }

    private List<FieldOperatorValue> validate(List<String> filterList) {
        List<FieldOperatorValue> dragonFilterList = new ArrayList<>();
        String filterRegex = "^([a-zA-Z_][a-zA-Z0-9_]*)-(eq|nq|gt|lt|gte|lte)-(.+)$";
        Pattern patter = Pattern.compile(filterRegex);

        for (String filter : filterList) {
            Matcher matcher = patter.matcher(filter);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(
                        String.format("Filter (%s) doesn't match", filter)
                );
            }
            String fieldAsString = matcher.group(1);
            String operatorAsString = matcher.group(2);
            String valueAsString = matcher.group(3);


            dragonFilterList.add(new FieldOperatorValue(
                    fieldAsString,
                    operatorAsString,
                    valueAsString
            ));
        }
        return dragonFilterList;
    }

    public List<DragonDto> filter(List<DragonDto> dragonDtoList, List<String> filterListAsString) {
        List<DragonDto> resultList = new ArrayList<>();
        List<FieldOperatorValue> filterList = validate(filterListAsString);
        for (DragonDto dragonDto: dragonDtoList) {
            for (FieldOperatorValue fieldOperatorValue: filterList) {
                String fieldAsString = fieldOperatorValue.getField();
                String operator = fieldOperatorValue.getOperator();
                String valueAsString = fieldOperatorValue.getOperator();


                switch (fieldAsString) {
                    case "id":
                        try {
                            Long value = Long.parseLong(fieldAsString);
                            if (genericFilter.matches(dragonDto.getId(), operator, value)) {
                                resultList.add(dragonDto);
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(
                                    String.format("Value (%s) doesn't match field (%s)", valueAsString, fieldAsString)
                            );
                        }
                        break;
                    default:
                        resultList.add(dragonDto);
                }
            }
        }

        return resultList;
    }
}
