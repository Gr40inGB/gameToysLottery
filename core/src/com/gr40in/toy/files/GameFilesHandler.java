package com.gr40in.toy.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gr40in.toy.model.Toy;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameFilesHandler {
    final private String patchList = "listGSON";
    final private String patchCount = "countMapGSON";
    private Gson dump;

    public GameFilesHandler() {
        this.dump = new GsonBuilder().setPrettyPrinting().create();
    }

    public void save(List<Toy> listToSave, String newPatch) {
        try (FileWriter fw = new FileWriter(new File(newPatch))) {
            fw.write(this.dump.toJson(listToSave));
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Map<Integer, Integer> toysMapToSave, String newPatch) {
        try (FileWriter fw = new FileWriter(new File(newPatch))) {
            fw.write(this.dump.toJson(toysMapToSave));
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFull(Map<Toy, Integer> toysMapToSave, String newPatch) {
        try (FileWriter fw = new FileWriter(new File(newPatch))) {
            fw.write(this.dump.toJson(toysMapToSave));
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, Integer> getCountFromGSON() throws FileNotFoundException {
        Type type = new TypeToken<HashMap<Integer, Integer>>() {
        }.getType();
//        System.out.println(this.dump.fromJson(new FileReader("mapGSON"), type));
        return this.dump.fromJson(new FileReader("mapGSON"), type);
    }

    public List<Toy> getToysFromGSON() throws FileNotFoundException {
        Type type = new TypeToken<List<Toy>>() {
        }.getType();
//        System.out.println(this.dump.fromJson(new FileReader("listGSON"), type));
        return this.dump.fromJson(new FileReader("listGSON"), type);
    }

}
