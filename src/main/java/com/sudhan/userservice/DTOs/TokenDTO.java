package com.sudhan.userservice.DTOs;

import com.sudhan.userservice.model.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TokenDTO {
    private String tokenValue;
    private Date expiryAt;
    private String email;

    public static TokenDTO from(Token token){
        if (token == null){
            return null;
        }
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setTokenValue(token.getTokenValue());
        tokenDTO.setExpiryAt(token.getExpiryAt());
        tokenDTO.setEmail(token.getUser().getEmail());
        return tokenDTO;
    }
}
