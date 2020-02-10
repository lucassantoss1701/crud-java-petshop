package br.com.luan.petshop.owner.dao;

import br.com.luan.petshop.configuration.JDBCConnection;
import br.com.luan.petshop.owner.model.Owner;

import java.sql.*;

public class OwnerDAO {

    private Connection connection = JDBCConnection.getConnection();

    public Owner create(Owner owner) {
        String sql = "INSERT INTO OWNER (NAME, PHONE) VALUE (?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, owner.getName());
            preparedStatement.setString(2, owner.getPhone());

            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected == 1) {
                System.out.println("Dono cadastrado com sucesso");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                owner.setId(resultSet.getLong(1));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return owner;
    }
}
