package com.gr40in.toy.model;

public class Toy {
    private int toy_id;
    private String name;
    private int difficulty;
    private String imagePatch;

    @Override
    public String toString() {
        return "Toy{" +
                "toy_id=" + toy_id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", imagePatch='" + imagePatch + '\'' +
                '}';
    }

    public Toy(int toy_id, String name, int difficulty, String imagePatch) {
        this.toy_id = toy_id;
        this.name = name;
        this.difficulty = difficulty;
        this.imagePatch = imagePatch;
    }

    public int getToy_id() {
        return toy_id;
    }

    public void setToy_id(int toy_id) {
        this.toy_id = toy_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setImagePatch(String imagePatch) {
        this.imagePatch = imagePatch;
    }

    public String getImagePatch() {
        return imagePatch;
    }
}
