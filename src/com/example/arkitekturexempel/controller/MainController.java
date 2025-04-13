package com.example.arkitekturexempel.controller;

import com.example.arkitekturexempel.model.Post;
import com.example.arkitekturexempel.service.PostService;
import com.example.arkitekturexempel.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController {

    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu(){

        while(true){
            System.out.println("---- gör ett val ----");
            System.out.println("1. lägg till ny användare");
            System.out.println("2. skapa nytt inlägg");
            System.out.println("3. hämta inlägg från användare");
            System.out.println("4. hämta inlägg med ord");
            System.out.println("0. avsluta");

            String choice = scanner.nextLine();
            switch (choice){
                case "1" -> createUser();
                case "2" -> createPost();
                case "3" -> getPostsByUserid();
                case "4" -> getPostsByWord();
                case "0" -> {
                    System.out.println("hej då!");
                    scanner.close();
                    return;
                }
            }
        }

    }

    public void getPostsByWord(){
        System.out.println("ange ett filter ord");

            String word = scanner.nextLine();

            List<Post> posts = postService.getPostsByWordInContent(word);
        if(posts.isEmpty()){
            System.out.println("inga inlägg som innehåller "+ word + " hittades");
        }{
            for (Post p : posts){
                System.out.println(p.getTitle() + " : " + p.getContent());
            }
        }
    }

    private void getPostsByUserid(){

        System.out.println("ange användarid");
        try{
            int id = Integer.parseInt(scanner.nextLine());
           // List<Post> posts = postService.getPostsFromUser(id);  //filtrering sker i databasen
            List<Post> posts = postService.getPostsByUserId(id); //filtrering sker i service lagret

            if(posts.isEmpty()){
                System.out.println("inga inlägg av den användaren hittades");
            }{
                for (Post p : posts){
                    System.out.println(p.getTitle() + " : " + p.getContent());
                }
            }
        } catch (NumberFormatException e){
            System.out.println("inte ett heltal");
        }
    }

    private void createPost(){
        System.out.println("ange användar Id:");
        try{
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("ange titel:");
            String title = scanner.nextLine();
            System.out.println("ange innehåll:");
            String content = scanner.nextLine();

            int result = postService.createPost(title,content,id);
            if(result > 0){
                System.out.println("inlägg skapat");
            } else {
                System.out.println("ingen inlägg skapades");
            }
        } catch (NumberFormatException e){
            System.out.println("inte ett heltal");
        }




    }

    private void createUser() {
        System.out.println("ange användarnamn:");
        String name = scanner.nextLine();
        int result = userService.createUser(name);
        if(result > 0){
            System.out.println("användare " + name + " tillagd!");
        } else {
            System.out.println("ingen användare lades till");
        }
    }
}
