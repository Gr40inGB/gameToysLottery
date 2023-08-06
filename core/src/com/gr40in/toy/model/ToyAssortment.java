package com.gr40in.toy.model;

import java.util.List;

public interface ToyAssortment {
    public void addToy(Toy toy);

    public void addToy(List<Toy> toyList);

    public List<Toy> getToys();
}
