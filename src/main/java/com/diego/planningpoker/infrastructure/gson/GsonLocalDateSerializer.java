package com.diego.planningpoker.infrastructure.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GsonLocalDateSerializer implements JsonSerializer<LocalDate> {

    public static final GsonLocalDateSerializer INSTANCE = new GsonLocalDateSerializer();

    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
        if(date != null)
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_DATE));
        return null;
    }

}
