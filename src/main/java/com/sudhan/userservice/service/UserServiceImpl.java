package com.sudhan.userservice.service;

import com.sudhan.userservice.Exceptions.InvalidTokenException;
import com.sudhan.userservice.Exceptions.PasswordMismatchException;
import com.sudhan.userservice.model.Token;
import com.sudhan.userservice.model.User;
import com.sudhan.userservice.repository.TokenRepository;
import com.sudhan.userservice.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signUp(String name, String email, String password) {
        //Check if there's already a user with the email or not.
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            //redirect to the login page.
            return optionalUser.get();
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        // User BCryptPasswordEncoder to encode the password.
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) throws PasswordMismatchException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()){
            return null;
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
            // login successful
            throw new PasswordMismatchException("Incorrect Password");
        }

        Token token = new Token();
        token.setUser(user);

        token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date expiryDate = calendar.getTime();
        token.setExpiryAt(expiryDate);

        return tokenRepository.save(token);
    }

    @Override
    public User validateToken(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findTokenByTokenValueAndExpiryAtGreaterThan(
                tokenValue,
                new Date());

        if (optionalToken.isEmpty()) {
            //Invalid token
            throw new InvalidTokenException("Invalid token.");
        }

        //Token is valid.
        Token token = optionalToken.get();
        return token.getUser();
    }
}
