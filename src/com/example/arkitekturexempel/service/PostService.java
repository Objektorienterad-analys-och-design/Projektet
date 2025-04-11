package com.example.arkitekturexempel.service;

import com.example.arkitekturexempel.model.Post;
import com.example.arkitekturexempel.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    private final PostRepository postRepository = new PostRepository();

    public int createPost(String title, String content, int userId){
        return postRepository.addPost(title,content,userId);

    }

    public List<Post> getPostsFromUser(int userId){
        return postRepository.getPostsByUser(userId);
    }

    public List<Post> getPostsByUserId(int userId){
        List<Post> unfilteredList = postRepository.getAllPosts();

        List<Post> filteredList = unfilteredList
                .stream()
                .filter(p -> p.getId() == userId)
                .toList();

        return filteredList;
    }

    public List<Post> getPostsByWordInContent(String word){
        List<Post> unfilteredList = postRepository.getAllPosts();

        List<Post> filteredList = unfilteredList
                .stream()
                .filter(p -> p.getContent().toLowerCase().contains(word))
                .toList();

        return filteredList;
    }


}
