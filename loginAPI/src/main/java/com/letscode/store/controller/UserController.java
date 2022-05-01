package com.letscode.store.controller;

import com.letscode.store.dto.UserDTO;
import com.letscode.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveUser(@RequestBody @Valid UserDTO userDTO){
        userService.saveUser(userDTO);
    }

    @GetMapping
    public Principal getUser(Principal principal){
        return principal;
    }

    @PutMapping("/{username}")
    public void updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable String username){
        userService.updateUser(userDTO, username);
    }
}
