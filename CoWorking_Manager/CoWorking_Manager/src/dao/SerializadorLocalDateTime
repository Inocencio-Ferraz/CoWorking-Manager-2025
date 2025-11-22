package dao;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class SerializadorLocalDateTime implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {


    // Json -> Objeto
	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		  return LocalDateTime.parse(
	                json.getAsString(), DateTimeFormatter.ofPattern("dd/MM/yyyy H:m")
	        );
	}

	 // Objeto -> Json
	@Override
	public JsonElement serialize(LocalDateTime data, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive( data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:m")));
	}



}
