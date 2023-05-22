package com.example.movieforum.service;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.exception.UserNotFoundException;
import com.example.movieforum.repository.AppUserRepository;
import com.example.movieforum.validate.user.validator.UserValidator;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final UserValidator userValidator;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        this.userValidator = new UserValidator();
    }

    public AppUser saveUser(AppUser user) {
        userValidator.validate(user);
        return appUserRepository.save(user);
    }

    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public AppUser getAppUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public AppUser updateUser(Long id, AppUser updatedUser) {
        AppUser user = getUserById(id);
        if (updatedUser.getFirstname() != null) {
            user.setFirstname(updatedUser.getFirstname());
        }
        if (updatedUser.getLastname() != null) {
            user.setLastname(updatedUser.getLastname());
        }
        if (updatedUser.getJoinDate() != null) {
            user.setJoinDate(updatedUser.getJoinDate());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getRole() != null) {
            user.setRole(updatedUser.getRole());
        }

        userValidator.validate(user);
        return saveUser(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        AppUser user = getUserById(id);
        appUserRepository.deleteById(id);
    }
}
