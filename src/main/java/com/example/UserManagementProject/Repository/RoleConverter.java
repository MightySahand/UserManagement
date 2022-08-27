package com.example.UserManagementProject.Repository;

import javax.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return switch (role) {
            case Admin -> 1;
            case Client -> 2;
            case Creator -> 3;
        };
    }

    @Override
    public Role convertToEntityAttribute(Integer role) {
        return switch (role) {
            case 1 -> Role.Admin;
            case 2 -> Role.Client;
            case 3 -> Role.Creator;
            default -> throw new IllegalArgumentException("Not even a Role!");
        };
    }

}
