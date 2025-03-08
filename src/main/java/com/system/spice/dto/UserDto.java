package com.system.spice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UserDto implements Serializable {
    String username;
    String fullName;
    String email;
    String numberPhone;
    Instant createAt;
    Instant updateAt;
    RoleDto role;
    String token;
}