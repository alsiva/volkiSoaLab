package volki.soalab.dragonManipulator.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenericComparablePair<T extends Comparable<T>> {
    private T first;
    private T second;

    public int compare() {
        return first.compareTo(second);
    }
}
