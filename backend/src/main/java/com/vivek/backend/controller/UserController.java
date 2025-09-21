package com.vivek.backend.controller;

import com.vivek.backend.model.User;
import com.vivek.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User newUser) {
        return repo.findById(id).map(u -> {
            u.setName(newUser.getName());
            u.setEmail(newUser.getEmail());
            u.setEmail(newUser.getPhone());
            u.setRole(newUser.getRole());
            return repo.save(u);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
