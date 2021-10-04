package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.project.fitstyle.model.User;
import ru.project.fitstyle.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:3000") // Дать доступ реакту
@RestController //Контроллер
@RequestMapping() // Не добавлял иерархию контроллеров, по ходу добавим
public class UserController {

    // @Autowired <- Так не делать, через конструктор!!
    private UserRepository userRepository;

    @Autowired  // <- Вот так, только над констуркторами (Автовнедрение DI)
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users") // Куда отправятся json
    public List<User> getUsers() {
        return userRepository.findAll(); // Отправятся в виде json
    }

    @PostMapping("/users") // Получаем
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
