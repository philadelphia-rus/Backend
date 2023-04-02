package com.philadelphia.api.Services;

import com.philadelphia.api.DAO.SuccessedDAO;
import com.philadelphia.api.DAO.UnitDao;
import com.philadelphia.api.DAO.UsersDAO;
import com.philadelphia.api.DTO.*;
import com.philadelphia.api.Database.Steps;
import com.philadelphia.api.Database.Successed;
import com.philadelphia.api.Database.Units;
import com.philadelphia.api.Database.Users;
import com.philadelphia.api.Errors.Failed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private SuccessedDAO successedDAO;
    @Autowired
    private UnitDao unitDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registration(RegisterDTO registerDTO) {
        List<Users> exists = usersDAO.getBylogin(registerDTO.getLogin());
        if (exists.size() != 0) {
            throw new Failed("Exists this login");
        }
        usersDAO.addUser(registerDTO.getLogin(), passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getName());
        for (Units unit : unitDao.getAll()) {
            for (Steps step : unit.getSteps()) {
                successedDAO.saveSuccessed(Successed.builder().steps(step).user(usersDAO.getBylogin(registerDTO
                        .getLogin()).get(0)).isCompleted(false).units(unit).build());
            }
        }
    }

    @Transactional(readOnly = true)
    public IdDTO login(LoginDTO loginDTO) {
        List<Users> users = usersDAO.getBylogin(loginDTO.getLogin());
        if (users.size() == 0) {
            throw new Failed("No such user");
        }
        Users user = users.get(0);
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new Failed("Bad password");
        }
        return new IdDTO(user.getId());
    }

    @Transactional(readOnly = true)
    public UserInfoDTO getInfo(Long id) {
        Users users = usersDAO.getById(id);
        if (users == null) {
            throw new Failed("No such user");
        }
        return UserInfoDTO.builder().login(users.getLogin()).name(users.getName()).xp(users.getXp())
                .money(users.getMoney()).build();
    }
    public void addXpToUser(Long userId, Long xp){
        Users user = usersDAO.getById(userId);
        user.setXp(user.getXp() + xp);
    }
    public void addMoneyToUser(Long userId, Long money){
        Users user = usersDAO.getById(userId);
        user.setMoney(user.getMoney() + money);
    }
    public List<LeaderBordDTO> getLeaderBord(){
        List<Users> users = usersDAO.getAllUsers();
        List<LeaderBordDTO> leaderBord = new ArrayList<>();
        users.sort((o1, o2) -> (int) (o2.getXp() - o1.getXp()));
        for(Users user: users){
            leaderBord.add(LeaderBordDTO.builder().name(user.getName()).xp(user.getXp()).build());
        }
        return leaderBord;
    }
}
