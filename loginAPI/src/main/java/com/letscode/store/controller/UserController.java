package com.letscode.store.controller;

import com.letscode.store.dto.UserDTO;
import com.letscode.store.service.MongoAuthUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final MongoAuthUserDetailService mongoAuthUserDetailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO saveUser(@RequestBody @Valid UserDTO userDTO){

        return mongoAuthUserDetailService.saveUser(userDTO);
    }

    @GetMapping
    public Principal getUser(Principal principal){
        return principal;
    }

//    @PutMapping("/{username}")
//    public void updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable String username){
//        mongoAuthUserDetailService.updateUser(userDTO, username);
//    }
}
