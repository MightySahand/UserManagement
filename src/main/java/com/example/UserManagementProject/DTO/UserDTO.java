package com.example.UserManagementProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "{USER_NOT_FOUND}")
    private String userName;
    private String password;

}
