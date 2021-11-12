package com.burmantov.simpleapp.controllers;

import com.burmantov.di.annotations.Bean;
import com.burmantov.di.annotations.Inject;
import com.burmantov.simpleapp.models.Cat;
import com.burmantov.simpleapp.repositories.AnimalRepoImpl;
import com.burmantov.simpleapp.services.AnimalCell;
import com.burmantov.simpleapp.services.AnimalCellImpl;

import java.rmi.NotBoundException;
import java.util.Set;
import java.util.stream.Collectors;
@Bean
public class AnimalControllerImpl implements AnimalController {

    private AnimalCellImpl service;

    @Inject
    public AnimalControllerImpl(AnimalCellImpl animalCell){
        this.service = animalCell;

    }

    public String getCatById(String id){
        try {
            return service.getById(id).toString();
        } catch (NotBoundException e) {
            return e.getMessage();
        }
    }
    public String saveCat(Cat cat){
        try {
            Cat saved = service.save(cat);
            return saved.toString();
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public String getAllCats(){
        return service.getAll().stream().map(Cat::toString).collect(Collectors.joining(", "));
    }

}
