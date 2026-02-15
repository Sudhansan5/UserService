package com.sudhan.userservice.service;


import com.sudhan.userservice.model.Token;
import com.sudhan.userservice.model.User;

public interface UserService {
    User signUp(String name, String email, String password);

    Token login(String email, String password);

    User validateToken(String tokenValue);
}
