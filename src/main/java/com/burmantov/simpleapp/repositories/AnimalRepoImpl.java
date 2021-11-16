package com.burmantov.simpleapp.repositories;



import com.burmantov.di.annotations.Bean;
import com.burmantov.simpleapp.models.Cat;

import java.util.*;
@Bean
public class AnimalRepoImpl implements AnimalRepo{
    private final Map<UUID, Cat> uuidCatMap;
    public AnimalRepoImpl(){
        uuidCatMap = new HashMap<>();
    }

    public Cat findById(UUID id){
        return uuidCatMap.get(id);
    }

    public Cat save(Cat cat){
        if (Objects.isNull(cat.getId())){
            System.out.println("Ты дурка?");
        } else {
            uuidCatMap.put(cat.getId(), cat);
        }return cat;
    }

    public Set<Cat> finAll(){
        return new HashSet<>(uuidCatMap.values());
    }



}
