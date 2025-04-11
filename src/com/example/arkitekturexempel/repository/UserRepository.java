package com.example.arkitekturexempel.repository;

import com.example.arkitekturexempel.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {

    public int addUser(String name){
        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO users(name) VALUES (?)";

            PreparedStatement ptst = conn.prepareStatement(sql);

            ptst.setString(1,name);
            return ptst.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;
        }
    }

}
