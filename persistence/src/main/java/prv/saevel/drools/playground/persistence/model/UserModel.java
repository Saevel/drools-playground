package prv.saevel.drools.playground.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    private int age;

    @OneToMany
    private List<AccountModel> accounts = new LinkedList<>();
}