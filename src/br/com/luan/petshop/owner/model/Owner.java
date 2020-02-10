package br.com.luan.petshop.owner.model;

public class Owner {

    private Long id;
    private String name;
    private String phone;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Owner setId(Long id) {
        this.id = id;
        return this;
    }

    public Owner setName(String name) {
        this.name = name;
        return this;
    }

    public Owner setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
