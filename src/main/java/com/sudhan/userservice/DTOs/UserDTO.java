package com.sudhan.userservice.DTOs;

import com.sudhan.userservice.model.Role;
import com.sudhan.userservice.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String email;
    private List<Role> roles;

    public static UserDTO from(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getId());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
