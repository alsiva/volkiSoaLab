package com.example.secondservice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JacksonXmlRootElement(localName = "dragon")
@NoArgsConstructor
public class DragonDto {
    // name не может быть null и пустым
    @NotNull
    @NotEmpty
    @JacksonXmlProperty(localName = "name")
    private String name;

    // coordinates не может быть null
    @NotNull(message = "Coordinates cannot be null")
    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesDto coordinates;

    // creationDate генерируется автоматически, не может быть null
    @JacksonXmlProperty(localName = "creationDateRolex")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creationDate;

    // age должно быть больше 0, может быть null
    @Min(value = 1, message = "Age must be greater than 0")
    @JacksonXmlProperty(localName = "age")
    private Long age;

    // wingspan должно быть больше 0, может быть null
    @Min(value = 1, message = "Wingspan must be greater than 0")
    @JacksonXmlProperty(localName = "wingspan")
    private Float wingspan;

    // speaking (без дополнительных ограничений)
    @JacksonXmlProperty(localName = "speaking")
    private Boolean speaking;

    // color не может быть null
    @NotNull(message = "Color cannot be null")
    @JacksonXmlProperty(localName = "color")
    private ColorDto color;

    // head (без дополнительных ограничений)
    @JacksonXmlProperty(localName = "head")
    private DragonHeadDto head;

}
