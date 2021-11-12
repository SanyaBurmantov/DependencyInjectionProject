package com.burmantov.simpleapp.services;

import com.burmantov.simpleapp.models.Cat;

import java.rmi.NotBoundException;
import java.util.Set;

public interface AnimalCell {
    public Cat getById(String id) throws NotBoundException;
    public Cat save(Cat cat);
    public Set<Cat> getAll();
}
