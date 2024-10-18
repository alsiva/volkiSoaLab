package volki.soalab.dto;

import volki.soalab.entities.ColorEntity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "color")
@XmlEnum
public enum ColorDto {
    @XmlEnumValue("GREEN") GREEN,
    @XmlEnumValue("BLUE") BLUE,
    @XmlEnumValue("ORANGE") ORANGE,
    @XmlEnumValue("WHITE") WHITE,
    @XmlEnumValue("BROWN") BROWN;

    public static ColorDto fromEntity(ColorEntity colorEntity) {
        if (colorEntity == null || colorEntity.getName() == null) {
            throw new IllegalArgumentException("Color entity or name is null");
        }
        return ColorDto.valueOf(colorEntity.getName().toUpperCase());

    }
}
