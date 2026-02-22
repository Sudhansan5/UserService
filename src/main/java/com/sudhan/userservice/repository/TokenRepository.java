package com.sudhan.userservice.repository;

import com.sudhan.userservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByTokenValue(String tokenValue);


    Token save(Token token);

    Optional<Token> findTokenByTokenValueAndExpiryAtGreaterThan(String tokenValue, Date expiryAt);
}
