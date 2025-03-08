package com.system.spice.model.system;

import lombok.Data;

@Data
public class ChangePasswordModel {
    String username;
    String oldPassword;
    String newPassword;
}
