package br.com.luan.petshop.pet.controller;

import br.com.luan.petshop.pet.dao.PetDAO;
import br.com.luan.petshop.pet.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetController {

    private PetDAO petDAO = new PetDAO();

    public void create(Pet pet) {
        petDAO.create(pet);
    }

    public List<Pet> search(String name){
        return petDAO.search(name);
    }

    public Pet findById(Long id){
        return petDAO.findById(id);
    }

    public void delete(Long id){
        petDAO.delete(id);
    }

    public Pet update(Pet pet){ return petDAO.update(pet);
    }

}
