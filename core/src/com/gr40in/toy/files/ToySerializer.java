package com.gr40in.toy.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.gr40in.toy.model.Toy;

import java.lang.reflect.Type;

public class ToySerializer implements JsonSerializer<Toy> {

    @Override
    public JsonElement serialize(Toy src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
