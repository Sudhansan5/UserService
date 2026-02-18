package com.sudhan.userservice.Controllers;

import com.sudhan.userservice.DTOs.LoginRequestDTO;
import com.sudhan.userservice.DTOs.SignupRequestDTO;
import com.sudhan.userservice.DTOs.TokenDTO;
import com.sudhan.userservice.DTOs.UserDTO;
import com.sudhan.userservice.model.User;
import com.sudhan.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignupRequestDTO requestDto) {
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return UserDTO.from(user);
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginRequestDTO requestDto) {
        return null;
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDTO validateToken(@PathVariable("tokenValue") String tokenValue) {
        return null;
    }
}
