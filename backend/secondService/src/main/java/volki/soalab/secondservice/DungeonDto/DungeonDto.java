package volki.soalab.secondservice.DungeonDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JacksonXmlRootElement(localName = "dungeon")
@NoArgsConstructor
public class DungeonDto {
    @NotNull(message = "Field cannot be null")
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    @JacksonXmlProperty(localName = "size")
    private Integer size;
}
