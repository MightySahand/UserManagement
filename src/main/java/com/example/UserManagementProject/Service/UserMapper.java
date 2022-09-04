package com.example.UserManagementProject.Service;


import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Repository.Role;
import com.example.UserManagementProject.Repository.RoleConverter;
import com.example.UserManagementProject.Repository.UserEntity;
import com.example.UserManagementProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// User mapper is used for mapping the objects
@Component
public class UserMapper {

    private static UserRepo userRepo;
    private static RoleConverter roleConvertor;

    @Autowired
    public UserMapper(UserRepo userRepo, RoleConverter roleConverter) {
        this.userRepo = userRepo;
        this.roleConvertor = roleConverter;
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
    public static UserEntity toUserEntity(UserModel userModel, Role role) {
        if (userRepo
                .existsByUserName(userModel.getUserName())) {
            return userRepo.findByUserName(
                    userModel.getUserName());
        }
        userRepo.save(
                new UserEntity(
                        userModel.getUserName(),
                        userModel.getPassword(),
                        roleConvertor.convertToDatabaseColumn(role)
                )
        );
        return userRepo.findByUserName(userModel.getUserName());
    }

    // UserModel -> UserDTO
    public static UserDTO toUserDTO(UserModel userModel) {
        return new UserDTO(userModel.getUserName(), userModel.getPassword());
    }

    // UserDTO -> UserEntity
    public static UserEntity toUserEntity(UserDTO userDTO, Role role) {
        if (userRepo.existsByUserName(userDTO.getUserName())) {
            return userRepo.findByUserName(userDTO.getUserName());
        }
        userRepo.save(
                new UserEntity(
                        userDTO.getUserName(),
                        userDTO.getPassword(),
                        roleConvertor.convertToDatabaseColumn(role))
        );
        return userRepo.findByUserName(userDTO.getUserName());
    }

    // UserEntity -> UserDTO
    public static UserDTO toUserDTO(UserEntity userEntity) {
        return new UserDTO(userEntity.getUserName(), userEntity.getPassword());
    }
}