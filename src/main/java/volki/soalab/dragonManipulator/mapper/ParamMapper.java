package volki.soalab.dragonManipulator.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import volki.soalab.dragonManipulator.mapper.fieldMapper.DragonFields;
import volki.soalab.dragonManipulator.mapper.fieldMapper.DragonMapper;
import volki.soalab.dragonManipulator.mapper.fieldMapper.StringMapper;
import volki.soalab.dragonManipulator.paramsStringRepresenation.FilterAsString;
import volki.soalab.dto.Dragon.DragonDtoWithId;
import volki.soalab.exceptions.IllegalParamException;

@Component
public class ParamMapper {


    private final DragonMapper dragonMapper;
    private final StringMapper stringMapper;

    @Autowired
    public ParamMapper(DragonMapper dragonMapper, StringMapper stringMapper) {
        this.dragonMapper = dragonMapper;
        this.stringMapper = stringMapper;
    }
    @SuppressWarnings("unchecked")
    public GenericComparablePair<?> FilterMapper(DragonDtoWithId dragonDtoWithId, FilterAsString filterAsString) {

        DragonFields field = DragonFields.fromString(filterAsString.getField());

        Comparable<?> fromDragon = dragonMapper.map(field, dragonDtoWithId);
        Comparable<?> fromString = stringMapper.map(field, filterAsString.getValue());


        return generatePair(fromDragon, fromString, fromString.getClass());
    }

    @SuppressWarnings("unchecked")
    public GenericComparablePair<?> DragonsMapper(DragonDtoWithId firstDragon, DragonDtoWithId secondDragon, String sortField) {
        DragonFields field = DragonFields.fromString(sortField);
        Comparable<?> fromFirstDragon = dragonMapper.map(field, firstDragon);
        Comparable<?> fromSecondDragon = dragonMapper.map(field, secondDragon);

        return generatePair(fromFirstDragon, fromSecondDragon, fromSecondDragon.getClass());
    }


    private <T extends Comparable<T>> GenericComparablePair<T>  generatePair(Comparable<?> a, Comparable<?> b, Class<T> type)  {
        if (a.getClass() != type || type != b.getClass()) {
            throw new ClassCastException(String.format(
                    """
                    Mappers incorrect type
                    FirstType (%s)
                    SecondType (%s)
                    """, a.getClass(), b.getClass()
            ));
        }
        T first = type.cast(a);
        T second = type.cast(b);
        return new GenericComparablePair<>(first, second);
    }


}
