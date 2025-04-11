package com.example.arkitekturexempel.repository;

import com.example.arkitekturexempel.database.Database;
import com.example.arkitekturexempel.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    public int addPost(String title, String content, int userId){

        try {
            Connection conn = Database.getConnection();
            String sql = "INSERT INTO posts(title,content,user_id) VALUES (?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(sql);

            ptst.setString(1,title);
            ptst.setString(2,content);
            ptst.setInt(3,userId);

            return ptst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<Post> getAllPosts(){

        List<Post> posts = new ArrayList<>();

        String sql = "SELECT * FROM posts";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement pdst = conn.prepareStatement(sql);

            ResultSet rs = pdst.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int userId = rs.getInt("user_id");
                Post p = new Post(id,title,content,userId);
                posts.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> getPostsByUser(int userId){

        List<Post> posts = new ArrayList<>();

        String sql = "SELECT * FROM posts WHERE user_id = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement pdst = conn.prepareStatement(sql);

            pdst.setInt(1,userId);
            ResultSet rs = pdst.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");

                Post p = new Post(id,title,content,userId);
                posts.add(p);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }

        return posts;
    }
}
