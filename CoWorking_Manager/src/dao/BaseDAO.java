package dao;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
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
    
   
    public BaseDAO(String fileName, Class<T> type) {
        this.fileName = fileName;
        this.type = type;
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
    
    
    public T buscarPorId(String id) {
        return listar().stream()
            .filter(obj -> {
                try {
                    Object valor = obj.getClass().getMethod("getId").invoke(obj);
                    return valor != null && valor.equals(id);
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .orElse(null);
    }

    public List<T> listar(Class<T> clazz) {
        List<T> lista = new ArrayList<>();
        Path path = Paths.get(fileName);

        if (Files.exists(path)) { 
            try {
                String json = new String(Files.readAllBytes(path));
                
                if (!json.trim().isEmpty()) { 
                    Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
                    lista = gson.fromJson(json, listType);
                }
            } catch (IOException e) {
                
                System.err.println("Erro ao ler" + e.getMessage());
            }
        } 
        
        return lista;
    }
   
    protected void escreverArquivo(List<T> lista) {
        String json = gson.toJson(lista, 
                TypeToken.getParameterized(ArrayList.class, type).getType());

            try {
                
                Files.createDirectories(Paths.get(fileName).getParent());

              
                try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
                    writer.write(json);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
    public void salvarTodos(List<T> lista) {
        try {
            Files.createDirectories(Paths.get(fileName).getParent());
            try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
                gson.toJson(lista, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}