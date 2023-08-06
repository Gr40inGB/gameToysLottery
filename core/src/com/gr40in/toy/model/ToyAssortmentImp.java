package com.gr40in.toy.model;

import com.gr40in.toy.files.GameFilesHandler;

import java.util.List;
import java.util.Map;

public class ToyAssortmentImp implements ToyAssortment{

    private GameFilesHandler filesHandler;
    private List<Toy> toysList;
    private Map<Toy,Integer> toysMap;


    public ToyAssortmentImp(Map<Toy, Integer> toysMap) {
        this.toysMap = toysMap;
    }

    @Override
    public void addToy(Toy toy) {

    }

    @Override
    public void addToy(List<Toy> toyList) {

    }

    @Override
    public List<Toy> getToys() {
        return null;
    }
}
