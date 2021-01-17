package pl.wskz.spring_hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int role_id;
    private String name;
}
