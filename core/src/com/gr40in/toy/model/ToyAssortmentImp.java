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


    public ToyAssortmentImp() {
        this.filesHandler = new GameFilesHandler();

        try {
            this.toysList = this.filesHandler.getToysFromGSON();
            this.toysCount = this.filesHandler.getCountFromGSON();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(toysCount);

    }

    @Override
    public void removeOneToy(Toy toy) {
        this.toysCount.put(toy.getToy_id(), this.toysCount.get(toy.getToy_id() - 1));
        this.filesHandler.save(this.toysCount);
        this.filesHandler.addWinner(toy);
    }

    @Override
    public void change(Toy toy, Integer difficulty) {
        for (int i = 0; i < toysList.size(); i++) {
            if (toysList.get(i).equals(toy)) {
                toysList.get(i).setDifficulty(difficulty);
            }
        }
    }

    @Override
    public void addToy(Toy toy, Integer count) {
        boolean needAdd = true;
        for (Toy t : this.toysList) {
            if (t.equals(toy)) {
                needAdd = false;
            }
        }
        if (needAdd) {
            toysList.add(toy);
            this.toysCount.put(toy.getToy_id(), count);
        } else {
            this.toysCount.put(toy.getToy_id(), count + this.toysCount.get(toy.getToy_id()));
        }
    }

    @Override
    public List<Toy> getToys() {
        return this.toysList;
    }

    @Override
    public Map<Toy, Integer> getAssortment() {

        Map<Toy, Integer> toysMap = new HashMap<>();
        for (Toy toy : this.toysList) {
            toysMap.put(toy, toysCount.get(toy.getToy_id()));
        }
        return toysMap;
    }
}
