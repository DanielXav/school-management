package com.teachermanagement.daniellucas.dto;

import com.teachermanagement.daniellucas.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String authority;

    public UserDTO(UserModel userModel) {
        id = userModel.getId();
        username = userModel.getUsername();
        password = userModel.getPassword();
        authority = userModel.getAuthority();
    }
}
