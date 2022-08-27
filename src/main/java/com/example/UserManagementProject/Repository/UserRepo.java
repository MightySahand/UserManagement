package com.example.UserManagementProject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
