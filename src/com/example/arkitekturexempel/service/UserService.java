package com.example.arkitekturexempel.service;

import com.example.arkitekturexempel.repository.UserRepository;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public int createUser(String name){
        return repository.addUser(name);
    }

}
