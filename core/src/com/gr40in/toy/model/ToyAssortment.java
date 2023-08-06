package com.gr40in.toy.model;

import java.util.List;
import java.util.Map;

public interface ToyAssortment {
    public void addToy(Toy toy, Integer count);

    public List<Toy> getToys();

    public Map<Toy, Integer> getAssortment();

    public void removeOneToy(Toy toy);
    public void change(Toy toy, Integer difficulty);
}
