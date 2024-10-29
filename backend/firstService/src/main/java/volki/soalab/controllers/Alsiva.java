package volki.soalab.controllers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JacksonXmlRootElement(localName = "alsiva")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alsiva {

    @JacksonXmlProperty(localName = "ageOfAlsiva")
    public Long age;

    @JacksonXmlProperty(localName = "name")
    public String name;
}
