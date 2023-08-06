package com.gr40in.toy.model;

import java.util.List;
import java.util.Map;

public interface ToyAssortment {
    public void addToy(Toy toy);

    public void addToy(List<Toy> toyList);

    public List<Toy> getToys();

    public Map<Toy, Integer> getAssortment();
}
