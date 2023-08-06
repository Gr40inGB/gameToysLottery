package com.gr40in.toy.model;

import com.gr40in.toy.files.GameFilesHandler;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToyAssortmentImp implements ToyAssortment {

    private GameFilesHandler filesHandler;
    private List<Toy> toysList;
    private Map<Integer, Integer> toysCount;
//    private Map<Toy, Integer> toysMap;


    public ToyAssortmentImp()  {
        this.filesHandler = new GameFilesHandler();

        try {
            this.toysList = this.filesHandler.getToysFromGSON();
            this.toysCount = this.filesHandler.getCountFromGSON();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        this.toysMap = toysMap;
//        for (Toy toy:this.toysList){
//            toysMap.put(toy, toysCount.get(toy.getToy_id()));
//        }
//        System.out.println(this.toysMap);
    }

    @Override
    public void addToy(Toy toy) {

    }

    @Override
    public void addToy(List<Toy> toyList) {

    }

    @Override
    public List<Toy> getToys() {
        return this.toysList;
    }

    @Override
    public Map<Toy, Integer> getAssortment() {

        Map<Toy, Integer> toysMap = new HashMap<>();
        for (Toy toy:this.toysList){
            toysMap.put(toy, toysCount.get(toy.getToy_id()));
        }
        return toysMap;
    }
}
