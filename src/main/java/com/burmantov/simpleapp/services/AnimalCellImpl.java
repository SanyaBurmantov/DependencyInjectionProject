package com.burmantov.simpleapp.services;

import com.burmantov.di.annotations.Bean;
import com.burmantov.simpleapp.exceptions.WrongArgumentException;
import com.burmantov.simpleapp.models.Cat;
import com.burmantov.simpleapp.repositories.AnimalRepoImpl;

import java.rmi.NotBoundException;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Bean
public class AnimalCellImpl implements AnimalCell{
    private AnimalRepoImpl animalRepoImpl;

    public AnimalCellImpl(AnimalRepoImpl repo) {
        this.animalRepoImpl = repo;
    }

    public Cat getById(String id) throws NotBoundException {
        if (id == null || id == " ") {
            throw new WrongArgumentException("Null cannot be applied");
        }
        UUID uuid = UUID.fromString(id);
        Cat foundCat = animalRepoImpl.findById(uuid);
        if (Objects.isNull(foundCat)) {
            throw new WrongArgumentException("Animal not found");
        }
        return foundCat;
    }

    public Cat save(Cat cat) {
        if (cat.getName() == null) throw new IllegalArgumentException();
        return animalRepoImpl.save(cat);
    }

    public Set<Cat> getAll() {
        return animalRepoImpl.finAll();
    }
}
