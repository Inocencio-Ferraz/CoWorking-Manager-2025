package dao;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model_.Espaco;

public abstract class BaseDAO<T> implements PersistenciaDAO<T> {
    
    protected String fileName;
    private Class<T> type;
    protected Gson gson; 
    //GsonBuilder cria gson - setPrettyPrinting permite formatar livremente 
    //.create() finaliza a criação e devolve
   

    public BaseDAO(String fileName, Class<T> type) {
        this.fileName = fileName;
        this.type = type;
        // Mudei esse Gson, colocando os Serializadores
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new SerializadorLocalDateTime())
                .registerTypeAdapter(Espaco.class, new SerializadorEspaco())
                .create();
    }
    
    public List<T> listar() {
        return listar(type);
    }

    public void salvar(T obj) {
        List<T> lista = listar(type);
        lista.add(obj);
        escreverArquivo(lista);
    }
    
    public void excluir(T obj) {
    	List<T> lista = listar(type);
        if (lista.remove(obj)) { 
            escreverArquivo(lista); 
        }
    }
    
    
    public T buscarPorId(String id) { //buscar por id
        return listar(type).stream()
                .filter(obj -> obj.toString().contains(id))
                .findFirst()
                .orElse(null);
    }

   
    public List<T> listar(Class<T> clazz) {
        List<T> lista = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(fileName)));
            if (!json.trim().isEmpty()) { 
                Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
                // Mudei de new Gson para gson
                lista = gson.fromJson(json, listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

   
    protected void escreverArquivo(List<T> lista) {

        // toJson com Typetoken
        String json = gson.toJson(lista, TypeToken.getParameterized(ArrayList.class, type).getType());

        // Mudei para o writer.write(json)
        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void salvarTodos(List<T> lista) { //salva todos
        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
        	gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
