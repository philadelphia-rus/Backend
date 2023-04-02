package com.philadelphia.api.Secuirty;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceJwt {

    private final List<User> users;

    public UserServiceJwt() {
        this.users = Arrays.asList( //�������� ������� � ������
                new User("User", "1234", Collections.singleton(Role.USER)),
                new User("Admin", "12345", Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}
