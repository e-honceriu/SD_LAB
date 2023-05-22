package com.example.movieforum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String firstname;

    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate joinDate;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @NotBlank
    @Size(max = 100)
    private String password;

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(15));
    }

    public void hashPassword() {
        this.password = hashPassword(this.password);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
