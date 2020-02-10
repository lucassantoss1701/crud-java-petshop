package br.com.luan.petshop.owner.controller;

import br.com.luan.petshop.owner.dao.OwnerDAO;
import br.com.luan.petshop.owner.model.Owner;

public class OwnerController {

    private OwnerDAO ownerDAO = new OwnerDAO();

    public Owner create(Owner owner) {
        ownerDAO.create(owner);
        return owner;
    }

}
