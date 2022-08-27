package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.DTO.UserDTO;

public interface AdminService {
    // CRUD -> Create Read Update Delete
    void createAdmin(UserDTO userDTO) throws IllegalAccessException;

    UserModel readAdmin(UserDTO userDTO) throws IllegalAccessException;

    void updateAdmin(UserDTO userDTO);

    void deleteAdmin(UserDTO userDTO);

    boolean adminExists(UserDTO userDTO);

}
