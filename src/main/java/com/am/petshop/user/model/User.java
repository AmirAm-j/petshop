package com.am.petshop.user.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @NoArgsConstructor
 * The JPA specification requires that all persistent classes (@Entity) have a no-arg constructor.
 * The JPA specification requires that all persistent classes have a no-arg constructor.
 * Because the compiler automatically creates a default no-arg constructor when no other constructor is defined,
 * only classes that define constructors must also include a no-arg constructor."
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "firsname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email; //TODO: new valid annotation for email (of adesso)
    @Column(name = "phone")
    private String phone; //TODO: Regex or valid annotation


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}
