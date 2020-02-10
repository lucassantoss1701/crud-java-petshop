package br.com.luan.petshop.pet.model;

import br.com.luan.petshop.owner.model.Owner;

public class Pet {
    private Long id;
    private String name;
    private String breed;
    private Float weight;
    private Size size;
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Float getWeight() {
        return weight;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Size getSize() {
        return size;
    }

    public Pet setId(Long id) {
        this.id = id;
        return this;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public Pet setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public Pet setWeight(Float weight) {
        this.weight = weight;
        return this;
    }

    public Pet setSize(Size size) {
        this.size = size;
        return this;
    }

    public Pet setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public String toString() {
        return "["+id+"] - "+name+" - Dono["+ownerId+"]";
    }
}
