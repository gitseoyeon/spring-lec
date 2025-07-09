package org.example.prac.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User_jdbc {
    private Integer id;
    private String username;
    private String password;
}
