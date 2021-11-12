package com.burmantov.simpleapp.repositories;


import com.burmantov.simpleapp.models.Cat;

import java.util.Set;
import java.util.UUID;

public interface AnimalRepo {
    public Cat findById(UUID id);
    public Cat save(Cat cat);
    public Set<Cat> finAll();
}
