package com.example.twowaychoice.component;

import com.example.twowaychoice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyToken {
    public static final String AUTHORIZATION = "Authorization";
    public static final String UID = "uid";
    public static final String ROLE = "role";

    private Integer uid;
    private Role role;
}
