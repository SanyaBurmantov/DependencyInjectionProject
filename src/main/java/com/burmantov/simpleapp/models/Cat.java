package com.burmantov.simpleapp.models;

import java.util.Objects;
import java.util.UUID;

public class Cat {
    private final UUID id;
    private String name;
    private String age;

    public Cat(String name, String age) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) && Objects.equals(age, cat.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age='" + getAge() + '\'' +
                '}';
    }
}
