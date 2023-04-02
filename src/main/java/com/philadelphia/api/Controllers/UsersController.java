package com.philadelphia.api.Controllers;

import com.philadelphia.api.DTO.*;
import com.philadelphia.api.Services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Controller for users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @PostMapping
    @Operation(summary = "Registartion user")
    public void registration(@RequestBody RegisterDTO registerDTO){
        usersService.registration(registerDTO);
    }
    @PostMapping("login")
    @Operation(summary = "Login user into account")
    public IdDTO login(@RequestBody LoginDTO loginDTO){
        return usersService.login(loginDTO);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get info by user id")
    public UserInfoDTO getInfo(@PathVariable("id") Long id){
        return usersService.getInfo(id);
    }
    @GetMapping("addXp/{userId}/{xp}")
    @Operation(summary = "Add xp to user")
    public void addXpToUser(@PathVariable("userId") Long userId, @PathVariable("xp") Long xp){
        usersService.addXpToUser(userId, xp);
    }
    @GetMapping("addMoney/{userId}/{money}")
    @Operation(summary = "Add xp to user")
    public void addMoneyToUser(@PathVariable("userId") Long userId, @PathVariable("money") Long money){
        usersService.addMoneyToUser(userId, money);
    }
    @GetMapping("/leaderBord")
    @Operation(summary = "Get leader bord of users by xp")
    public List<LeaderBordDTO> getLeaderBord(){
        return usersService.getLeaderBord();
    }
}
