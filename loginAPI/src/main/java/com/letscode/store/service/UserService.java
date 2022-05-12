package com.letscode.store.service;
//
//import com.letscode.store.dto.UserDTO;
//import com.letscode.store.exception.AlreadyExistException;
//import com.letscode.store.exception.NotFoundException;
//import com.letscode.store.model.User;
//import com.letscode.store.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
////
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final MongoTemplate mongoTemplate;
//
//    private final AuthorityService authorityService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    public void saveUser(UserDTO userDTO) {
//        Optional<User> user = userRepository.findUserByUsername(userDTO.getUsername());
//        if(user.isPresent()) throw new AlreadyExistException("User Already Exist");
//
//        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        final User userDB = userRepository.save(User.convert(userDTO));
//
//        userDTO.getRoles().forEach(role -> authorityService.saveAuthorities(userDB, role));
//
//    }
//
//    public void updateUser(UserDTO userDTO, String username) {
//        Optional<User> user = userRepository.findUserByUsername(username);
//        if(user.isEmpty()) throw new NotFoundException("User Not Found");
//        user.get().setUserName(userDTO.getUsername());
//        user.get().setPassword(userDTO.getPassword());
//        user.get().setEnable(userDTO.getEnable());
//        final User userDB = userRepository.save(user.get());
//
//        userDTO.getRoles().forEach(role -> authorityService.saveAuthorities(userDB, role));
//    }
//}
