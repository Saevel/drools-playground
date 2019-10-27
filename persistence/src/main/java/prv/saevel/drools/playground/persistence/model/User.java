package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    private int age;

    @OneToMany
    private List<Account> accounts = new LinkedList<>();
}