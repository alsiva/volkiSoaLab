package com.example.secondservice;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public enum ColorDto {
    @JacksonXmlProperty(localName = "GREEN") GREEN,
    @JacksonXmlProperty(localName = "BLUE") BLUE,
    @JacksonXmlProperty(localName = "ORANGE") ORANGE,
    @JacksonXmlProperty(localName = "WHITE") WHITE,
    @JacksonXmlProperty(localName = "BROWN") BROWN;
}
