package pl.wskz.spring_hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor      // automatycznie dodaje kod do klasy związany z konstruktorem domyślnym
@AllArgsConstructor     // automatycznie dodaje kod do klasy związany z konstruktorem zawierającym wszystkie pola w agrumentach
@Data                   // automatycznie dodaje kod do klasy związany z getterami, setterami, toString
@Entity
@Table(name = "users")
public class User {
    @Id                                                 // klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inkrementacja
    private int userId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    @Transient
    private String passwordRepeat;
    private boolean status;
    @Column(name = "registration_date_time")
    private LocalDateTime registrationTime;
    // REALACJA N : M
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_to_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String lastName, String email, String password, String passwordRepeat) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        // inicjalizacja pozostałych pól
        this.status = false;
        registrationTime = LocalDateTime.now();
    }
}
