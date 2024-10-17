package volki.soalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class CoordinatesDto implements Comparable<CoordinatesDto> {
    @Max(value = 411, message = "x must be lower or equal than 411")
    @NotNull(message = "x cannot be null")
    private Double x; //Максимальное значение поля: 411, Поле не может быть null
    @Min(value = -296, message = "y must be greater or equal than -296")
    private long y; //Значение поля должно быть больше -296

    public CoordinatesDto(String coordinates) {
        String coordRegex = "^(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)$";
        Pattern pattern = Pattern.compile(coordRegex);
        Matcher matcher = pattern.matcher(coordinates);

        this.x = Double.parseDouble(matcher.group(1));
        this.y = Long.parseLong(matcher.group(3));
    }

    private double countDistance() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
    }


    @Override
    public int compareTo(CoordinatesDto o) {
        return Double.compare(this.countDistance(), o.countDistance());
    }
}
