package com.example.movieforum.controller;

import com.example.movieforum.entity.AppUser;
import com.example.movieforum.entity.LogInDetails;
import com.example.movieforum.exception.InvalidPasswordException;
import com.example.movieforum.exception.NoUserLoggedInException;
import com.example.movieforum.exception.UserAlreadyLoggedInException;
import com.example.movieforum.exception.UserNotFoundException;
import com.example.movieforum.service.AppUserService;
import com.example.movieforum.service.AuthService;
import com.example.movieforum.service.EmailService;
import com.example.movieforum.validate.ValidationException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;

@RestController
@Validated
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AppUserController {

    private final AppUserService appUserService;
    private final AuthService authService;
    private final EmailService emailService;

    @Autowired
    public AppUserController(AppUserService appUserService, EmailService emailService, AuthService authService) {
        this.appUserService = appUserService;
        this.emailService = emailService;
        this.authService = authService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long userId) {
        try {
            AppUser user = appUserService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch(UserNotFoundException e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> logIn(@RequestBody LogInDetails logInDetails) {
        try {
            authService.login(logInDetails.getUsername(), logInDetails.getPassword());
        }catch (UserAlreadyLoggedInException e) {
            return ResponseEntity.badRequest().body("A user is already logged in");
        }catch (InvalidPasswordException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.ok("Logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut() {
        try {
            authService.logout();
            return ResponseEntity.ok("Logged out");
        }catch (NoUserLoggedInException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        user.setJoinDate(LocalDate.now());
        user.hashPassword();
        try {
            AppUser createdUser = appUserService.saveUser(user);
            emailService.sendRegistrationEmail(createdUser);
            return ResponseEntity.created(URI.create("/api/users" + createdUser.getId())).body(createdUser.toString());
        } catch (ValidationException | DataAccessException |MessagingException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody AppUser updatedUser) {
        try {
            AppUser user = appUserService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user.toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationException | DataAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser() {
        try {
            appUserService.deleteUser(authService.getCurrentUser().getId());
            authService.logout();
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NoUserLoggedInException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
