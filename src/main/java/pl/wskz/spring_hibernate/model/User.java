package pl.wskz.spring_hibernate.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
