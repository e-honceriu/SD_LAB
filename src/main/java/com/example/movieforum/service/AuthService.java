package com.example.movieforum.service;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.exception.InvalidPasswordException;
import com.example.movieforum.exception.NoUserLoggedInException;
import com.example.movieforum.exception.UserAlreadyLoggedInException;
import com.example.movieforum.exception.UserNotFoundException;
import com.example.movieforum.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserRepository appUserRepository;

    private AppUser currentUser;

    public AuthService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void login(String username, String password) throws UserNotFoundException {
        if (currentUser != null) {
            throw new UserAlreadyLoggedInException();
        }
        AppUser user = appUserRepository.findAppUserByUsername(username).
                orElseThrow(() -> new UserNotFoundException(username));
        if (!user.checkPassword(password)) {
            throw new InvalidPasswordException(username);
        }
        currentUser = user;
    }

    public void logout() {
        if (this.currentUser == null) {
            throw new NoUserLoggedInException();
        }
        this.currentUser = null;
    }

    public AppUser getCurrentUser() {
        if (currentUser == null) {
            throw new NoUserLoggedInException();
        }
        return currentUser;
    }
}
