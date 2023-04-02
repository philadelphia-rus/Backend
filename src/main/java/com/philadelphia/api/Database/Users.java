package com.philadelphia.api.Database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    @NotEmpty
    private String login;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @Column(name = "xp")
    private Long xp = 0L;
    @Column(name = "money")
    private Long money = 0L;
}
