package com.burmantov.simpleapp.controllers;

import com.burmantov.simpleapp.models.Cat;

public interface AnimalController {
    public String getCatById(String id);
    public String saveCat(Cat cat);
    public String getAllCats();
}


