package volki.soalab.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldValuePair<T> {
    private T value;
    private T field;

}
