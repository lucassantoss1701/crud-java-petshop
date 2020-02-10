package br.com.luan.petshop.pet.dao;

import br.com.luan.petshop.configuration.JDBCConnection;
import br.com.luan.petshop.pet.model.Pet;
import br.com.luan.petshop.pet.model.Size;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private Connection connection = JDBCConnection.getConnection();

    public Pet create(Pet pet) {
        String sql = "INSERT INTO Pet(NAME, BREED, WEIGHT, SIZE, OWNER_ID) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, pet.getName());
            preparedStatement.setString(2, pet.getBreed());
            preparedStatement.setFloat(3, pet.getWeight());
            preparedStatement.setString(4, pet.getSize().name());
            preparedStatement.setLong(5, pet.getOwnerId());

            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected == 1) {
                System.out.println("Salvo com sucesso");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                pet.setId(resultSet.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pet;

    }

    public Pet findById(Long id){
        String sql = "Select * from pet where id = ?";
        Pet pet = new Pet();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            ResultSet resultSet =  preparedStatement.executeQuery();

            if(resultSet.next()){
                pet.setId(resultSet.getLong("id"));
                pet.setBreed(resultSet.getString("breed"));
                pet.setName(resultSet.getString("name"));
                pet.setOwnerId(resultSet.getLong("owner_id"));
                pet.setSize(Size.valueOf(resultSet.getString("size")));
                pet.setWeight(resultSet.getFloat("weight"));
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public List<Pet> search(String name){
        String sql = "Select * from pet where name like ?";
        List<Pet> petList = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,"%"+name+"%");
            ResultSet resultSet =  preparedStatement.executeQuery();

            while(resultSet.next()){
                Pet pet = new Pet();
                pet.setId(resultSet.getLong("id"));
                pet.setBreed(resultSet.getString("breed"));
                pet.setName(resultSet.getString("name"));
                pet.setOwnerId(resultSet.getLong("owner_id"));
                pet.setSize(Size.valueOf(resultSet.getString("size")));
                pet.setWeight(resultSet.getFloat("weight"));

                petList.add(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return petList;
    }

    public void delete(Long id){
        String sql = "delete from pet where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Pet update(Pet pet){
        String sql = "update pet set name=?, breed=?, owner_id=?, size=?, weight=? where id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setString(2, pet.getBreed());
            preparedStatement.setLong(3, pet.getOwnerId());
            preparedStatement.setString(4, pet.getSize().name());
            preparedStatement.setFloat(5, pet.getWeight());

            preparedStatement.setLong(6, pet.getId());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return pet;
    }
}
