package dao;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

import model_.Espaco;

public class SerializadorEspaco implements JsonSerializer<Espaco>, JsonDeserializer<Espaco> {

    @Override
    public JsonElement serialize(Espaco t, Type tipo, JsonSerializationContext serializador) {

        JsonObject objeto = new JsonObject();

        // Cria um objeto Json do modelo:
        // "tipo": {Tipo da classe. ex: SalaDeReuniao}
        // "propriedades": {Restante das propriedades}
        objeto.add("tipo", new JsonPrimitive(t.getClass().getSimpleName()));
        objeto.add("propriedades", serializador.serialize(t, t.getClass()));

        return objeto;
    }

    @Override
    public Espaco deserialize(JsonElement json, Type tipo, JsonDeserializationContext deserializador) throws JsonParseException {

        // Carrega o objeto Json
        JsonObject objeto = json.getAsJsonObject();

        // Recupera a propriedade "tipo" (ex: Auditorio)
        String tipoEspaco = objeto.get("tipo").getAsString();

        // Recupera as demais propriedades
        JsonElement elemento = objeto.get("propriedades");

        try {
            // Cria o objeto Espaco usando o elemento "propriedades"
            //
            // em Class.forName é necessário passar o nome do pacote + o nome da classe
            // ex: model.SalaDeReuniao
            return deserializador.deserialize(elemento, Class.forName("model_." + tipoEspaco));
        }
        catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }



}
