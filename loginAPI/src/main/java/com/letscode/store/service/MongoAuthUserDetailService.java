package com.letscode.store.service;

import com.letscode.store.dto.UserDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.model.RolesEnum;
import com.letscode.store.model.User;
import com.letscode.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findUserByUsername(userDTO.getUsername());
        if(user.isPresent()) throw new AlreadyExistException("User Already Exist");

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userDB = userRepository.save(User.convert(UUID.randomUUID(), userDTO));

        return UserDTO.convert(userDB);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()){
            List<GrantedAuthority> authorities = getUserAuthority(user.get().getRoles());
            return buildUserForAuthentication(user.get(), authorities);
        }else {
            throw new UsernameNotFoundException("username not found");
        }

    }
    private List<GrantedAuthority> getUserAuthority(Set<RolesEnum> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.toString()));
        });

        return new ArrayList<>(roles);
    }
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> roles) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
