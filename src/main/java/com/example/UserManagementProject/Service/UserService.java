package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.DTO.UserDTO;

public interface UserService {
    // CRUD -> Create Read Update Delete
    void createUser(UserDTO userDTO);

    UserModel readUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(UserDTO userDTO);

    boolean userExists(UserDTO userDTO);

    boolean transferMoney(String token, String destUserName, double amount);
}
