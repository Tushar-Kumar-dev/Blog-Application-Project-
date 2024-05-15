package com.BlogApplication.BlogApplicationProject.services;

import com.BlogApplication.BlogApplicationProject.entity.User;
import com.BlogApplication.BlogApplicationProject.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto userDto, Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUser();
    void deleteUser(Long userId);
}
