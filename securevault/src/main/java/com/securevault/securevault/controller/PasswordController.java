package com.securevault.securevault.controller;

import com.securevault.securevault.model.Password;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @GetMapping("/get")
    Password getAllPasswords() {
        return null;
    }

    @PostMapping("/add")
    boolean createPassword(@RequestBody String software,
                           @RequestBody String username,
                           @RequestBody String password) {
        return false;
    }

    @PutMapping("/update")
    Password updatePassword(@RequestBody int id, @RequestBody Password oldPassword) {
        return null;
    }

    @DeleteMapping("/delete")
    boolean deletePassword(@RequestBody String id) {
        return false;
    }
}
