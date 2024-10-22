package volki.soalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "error")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    @XmlElement(name = "message")
    private String message;
}
