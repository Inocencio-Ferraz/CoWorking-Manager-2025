package dao;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.*;
import java.lang.reflect.Type;
import java.util.*;

public abstract class baseDAO<T> implements Persistencia<T> {
    
    protected String fileName;
    private Class<T> type;
    
  
    public baseDAO(String fileName, Class<T> type) {
        this.fileName = fileName;
        this.type = type;
    }
    
    public List<T> listar() {
        return listar(type);
    }

    public void salvar(T obj) {
        List<T> lista = listar();
        lista.add(obj);
        escreverArquivo(lista);
    }
    
    public void excluir(T obj) {
    	List<T> lista = listar();
        if (lista.remove(obj)) { 
            escreverArquivo(lista); 
        }
    }
    

   
    public List<T> listar(Class<T> clazz) {
        List<T> lista = new ArrayList<>();
        try {
           
            String json = new String(Files.readAllBytes(Paths.get(fileName)));
            if (!json.trim().isEmpty()) { 
                Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
                lista = new Gson().fromJson(json, listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

   
    protected void escreverArquivo(List<T> lista) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
            new Gson().toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
