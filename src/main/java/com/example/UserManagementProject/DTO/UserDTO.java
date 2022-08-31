package com.example.UserManagementProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "{USERNAME_IS_EMPTY}")
    private String userName = "";
    private String password = "";

}
