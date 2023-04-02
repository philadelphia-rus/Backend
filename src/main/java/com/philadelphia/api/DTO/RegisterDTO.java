package com.philadelphia.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String login;
    private String password;
    private String name;
}
