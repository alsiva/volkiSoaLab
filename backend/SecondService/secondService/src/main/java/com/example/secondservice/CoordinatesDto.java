package com.example.secondservice;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "coordinates")
public class CoordinatesDto {
    @Max(value = 411, message = "x must be lower or equal than 411")
    @NotNull(message = "x cannot be null")
    @NotEmpty(message = "x cannot be empty")
    @JacksonXmlProperty(localName = "x")
    private Double x; // Максимальное значение поля: 411, Поле не может быть null

    @Min(value = -296, message = "y must be greater or equal than -296")
    @JacksonXmlProperty(localName = "y")
    private Long y; // Значение поля должно быть больше -296

}
