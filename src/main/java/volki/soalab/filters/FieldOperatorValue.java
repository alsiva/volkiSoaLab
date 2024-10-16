package volki.soalab.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldOperatorValue {
    private String field;
    private String operator;
    private String value;
}
