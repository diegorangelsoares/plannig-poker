package com.diego.planningpoker.infrastructure.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GsonLocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

    public static final GsonLocalDateTimeSerializer INSTANCE = new GsonLocalDateTimeSerializer();

    @Override
    public JsonElement serialize(LocalDateTime date, Type type, JsonSerializationContext jsonSerializationContext) {
        if(date != null)
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_DATE_TIME));
        return null;
    }

}
