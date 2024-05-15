package com.BlogApplication.BlogApplicationProject.services.Impl;

import com.BlogApplication.BlogApplicationProject.config.AppConstant;
import com.BlogApplication.BlogApplicationProject.entity.Role;
import com.BlogApplication.BlogApplicationProject.entity.User;
import com.BlogApplication.BlogApplicationProject.exception.ResourceNotFoundException;
import com.BlogApplication.BlogApplicationProject.payloads.UserDto;
import com.BlogApplication.BlogApplicationProject.repositories.RoleRepo;
import com.BlogApplication.BlogApplicationProject.repositories.UserRepo;
import com.BlogApplication.BlogApplicationProject.services.UserService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findById(AppConstant.NORMAL_USER).get();

        user.getRoles().add(role);
        User newUser = userRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto user) {
        User user1 = DtoToUser(user);
        User savedUser = userRepo.save(user1);
        UserDto userDto = userToDto(savedUser);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id", userId));
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());

        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> user = userRepo.findAll();
        List<UserDto> user1 = user.stream().map(userToDto-> userToDto(userToDto)).collect(Collectors.toList());
        return user1;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " id", userId));
        userRepo.delete(user);
    }

    public UserDto userToDto(User user){
        UserDto user1= modelMapper.map(user,UserDto.class);

//        user1.setAbout(user.getAbout());
//        user1.setName(user.getName());
//        user1.setEmail(user.getEmail());
//        user1.setPassword(user.getPassword());
        return user1;
    }
    public User DtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());

        return user;
    }
}
