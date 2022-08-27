package com.example.UserManagementProject.Repository;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance = 0.0;

    @Column(name = "role")
    private Role role = Role.Client;

    public void addMoney(@NotNull double amount) {
        this.balance += amount;
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
