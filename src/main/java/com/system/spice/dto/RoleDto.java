package com.system.spice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Data
public class RoleDto implements Serializable {
    @NotNull
    String roleName;
    Set<UserDto> users;
}