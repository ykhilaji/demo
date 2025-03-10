package com.shipping.demo.realTime.task.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class JsonNodeWriteConverter implements Converter<JsonNode, Json> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Json convert(JsonNode source) {
        try {
            return Json.of(objectMapper.writeValueAsString(source));
        } catch (Exception e) {
            throw new RuntimeException("Erreur de conversion du JsonNode: " + e.getMessage(), e);
        }
    }
}

