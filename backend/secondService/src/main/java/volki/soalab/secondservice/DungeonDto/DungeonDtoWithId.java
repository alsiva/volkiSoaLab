package volki.soalab.secondservice.DungeonDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "dungeon")
@NoArgsConstructor
@JsonPropertyOrder({"id"})
public class DungeonDtoWithId extends DungeonDto {
    @Min(value = 1, message = "Id must be greater than 0")
    @JacksonXmlProperty(localName = "id")
    private Long id;
}
