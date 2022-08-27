package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Repository.Role;
import com.example.UserManagementProject.Repository.UserEntity;
import com.example.UserManagementProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    final UserRepo userRepo;

    @Autowired
    public AdminServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void createAdmin(UserDTO userDTO) throws IllegalAccessException {
        if (!userRepo.existsByUserName(userDTO.getUserName())) {
            UserEntity x = UserMapper.toUserEntity(userDTO);
            x.setRole(Role.Admin);
            userRepo.save(x);
        } else {
            throw new IllegalAccessException("There is already a user for your UserName!");
        }
    }

    @Override
    public UserModel readAdmin(UserDTO userDTO) throws IllegalAccessException {
        if (!userRepo.existsByUserName(userDTO.getUserName())) {
            throw new IllegalAccessException("No User is registered by that UserName!");
        } else {
            return UserMapper.toUserModel(userRepo.findByUserName(userDTO.getUserName()));
        }
    }

    @Override
    public void updateAdmin(UserDTO userDTO) {

    }

    @Override
    public void deleteAdmin(UserDTO userDTO) {

    }

    @Override
    public boolean adminExists(UserDTO userDTO) {
        return false;
    }
}
