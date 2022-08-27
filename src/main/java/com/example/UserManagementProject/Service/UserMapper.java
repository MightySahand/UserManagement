package com.example.UserManagementProject.Service;


import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Repository.UserEntity;
import com.example.UserManagementProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// User mapper is used for mapping the objects
@Component
public class UserMapper {

    private static UserRepo userRepo;

    @Autowired
    public UserMapper(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    // UserDTO -> UserModel
    public static UserModel toUserModel(UserDTO userDTO) {
        return new UserModel(userDTO.getUserName(), userDTO.getPassword());
    }

    // UserEntity -> UserModel
    public static UserModel toUserModel(UserEntity userEntity) {
        return new UserModel(userEntity.getUserName(), userEntity.getPassword());
    }

    // UserModel -> UserEntity
    public static UserEntity toUserEntity(UserModel userModel) {
        if (userRepo.existsByUserName(userModel.getUserName())) {
            return userRepo.findByUserName(userModel.getUserName());
        }
        userRepo.save(new UserEntity(userModel.getUserName(), userModel.getPassword()));
        return userRepo.findByUserName(userModel.getUserName());
    }

    // UserModel -> UserDTO
    public static UserDTO toUserDTO(UserModel userModel) {
        return new UserDTO(userModel.getUserName(), userModel.getPassword());
    }

    // UserDTO -> UserEntity
    public static UserEntity toUserEntity(UserDTO userDTO) {
        if (userRepo.existsByUserName(userDTO.getUserName())) {
            return userRepo.findByUserName(userDTO.getUserName());
        }
        userRepo.save(new UserEntity(userDTO.getUserName(), userDTO.getPassword()));
        return userRepo.findByUserName(userDTO.getUserName());
    }

    // UserEntity -> UserDTO
    public static UserDTO toUserDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getUserName(), userEntity.getPassword());
    }
}