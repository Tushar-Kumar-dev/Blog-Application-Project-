package com.BlogApplication.BlogApplicationProject.controllers;

import com.BlogApplication.BlogApplicationProject.payloads.ApiResponse;
import com.BlogApplication.BlogApplicationProject.payloads.UserDto;
import com.BlogApplication.BlogApplicationProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUser = this.userService.createUser(userDto);
        return new  ResponseEntity<>(createUser,HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long userId){
        UserDto updateUser = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
        UserDto getUser = userService.getUserById(userId);
        return ResponseEntity.ok(getUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> getUser = userService.getAllUser();
        return ResponseEntity.ok(getUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("user deleted Successfully", true),HttpStatus.OK);
    }
}
