package com.example.UserManagementProject.Service;

import com.example.UserManagementProject.DTO.UserDTO;
import com.example.UserManagementProject.Exceptions.InsufficientAmountOfMoneyException;
import com.example.UserManagementProject.Exceptions.IsNotLoggedInException;
import com.example.UserManagementProject.Exceptions.PasswordDoesNotMatchException;
import com.example.UserManagementProject.Exceptions.UserNotFoundException;
import com.example.UserManagementProject.Repository.Role;
import com.example.UserManagementProject.Repository.UserEntity;
import com.example.UserManagementProject.Repository.UserRepo;
import com.google.api.services.drive.Drive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //Repo Interface
    final UserRepo userRepo;

    // GoogleDriveService Object
    final GoogleDriveService googleDriveService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
        this.userRepo = userRepo;
        log.info("Service Layer is created!");
        userRepo.deleteAll();
        userRepo.save(new UserEntity("admin", "admin Password"));
        userRepo.save(new UserEntity("John", "Grishman"));
        userRepo.save(new UserEntity("Albert", "Camus"));
    }

    public String logIn(UserDTO userDTO) {
        log.warn("Searching for UserName!");
        // Checks if there is any UserEntity By The UserDTO UserName
        if (userRepo.existsByUserName(userDTO.getUserName())) {
            /*
             The User is Present
             Checks if UserDTO and UserEntity passwords are equal
            */
            log.warn("UserName Found!");
            log.warn("Verifying password!");
            if (userRepo.findByUserName(userDTO.getUserName())
                    .getPassword()
                    .equals(userDTO.getPassword())) {
                // Not Only the UserName is found but also The Password was
                // right
                log.info("Sending Token");
                return JWTToken.getToken(userDTO);
            } else {
                // The Password isn't Equal
                throw new PasswordDoesNotMatchException();
            }
        } else {
            //The UserName is not present
            log.warn("UserName NotFound!");
            throw new UserNotFoundException();
        }
    }


    public Double getBalanceStatus(String token) {
        if (!token.equals("") && JWTToken.ValidateToken(token)) {
            UserEntity Sender = userRepo.findByUserName(JWTToken.ExtractInfoFromToken(token, "username"));
            if (Sender != null) {
                return Sender.getBalance();
            }
            return 0.0;
        }
        throw new RuntimeException("You are not Logged in!");
    }

    // Admin Role Can only do this
    public List<UserDTO> getAllUsers() {
        List<UserEntity> list = (List<UserEntity>) userRepo.findAll();
        List<UserDTO> listDTO = new ArrayList<>();
        list.forEach(userEntity -> listDTO.add(UserMapper.toUserDTO(UserMapper.toUserModel(userEntity))));
        return listDTO;
    }

    public boolean transferMoney(String token, String destUserName, double amount) {
        // See if we are logged in
        if (!token.equals("") && JWTToken.ValidateToken(token)) {
            UserEntity sender = UserMapper.toUserEntity(
                    readUser(
                            JWTToken.ExtractInfoFromToken(token, "username")),
                    Role.Client);
            if (sender != null && sender.getBalance() >= amount) {
                UserEntity receiver = userRepo.findByUserName(destUserName);
                receiver.setBalance(receiver.getBalance() + amount);
                sender.setBalance(sender.getBalance() - amount);
                return true;
            }
            throw new InsufficientAmountOfMoneyException();
        }
        throw new IsNotLoggedInException();
    }

    @Override
    public void createUser(UserDTO userDTO) {
        if (!userExists(userDTO)) {
            userRepo.save(UserMapper.toUserEntity(UserMapper.toUserModel(userDTO), Role.Client));
        } else {
            throw new RuntimeException("Cannot create an account because the user is present");
        }
    }

    @Override
    public UserModel readUser(UserDTO userDTO) throws RuntimeException {
        if (userExists(userDTO)) {
            return UserMapper.toUserModel(userDTO);
        }
        throw new RuntimeException("User Not Found!");
    }


    public UserModel readUser(String userName) {
        if (userRepo.existsByUserName(userName)) {
            return UserMapper.toUserModel(userRepo.findByUserName(userName));
        }
        throw new UserNotFoundException();
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserMapper.toUserEntity(readUser(userDTO), Role.Client).setPassword(userDTO.getPassword());
    }

    @Override
    public void deleteUser(UserDTO userDTO) {
        userRepo.delete(UserMapper.toUserEntity(readUser(userDTO), Role.Client));
    }

    @Override
    public boolean userExists(UserDTO userDTO) {
        return userRepo.existsByUserName(userDTO.getUserName());
    }

    // Google API Services
    public String getAuthorizationURL() {
        return googleDriveService.getAuthorizationURL();
    }

    public void getService(String token) {
        try {
            googleDriveService.getService(googleDriveService.getCredentials(token));
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
