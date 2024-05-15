package com.BlogApplication.BlogApplicationProject.controllers;

import com.BlogApplication.BlogApplicationProject.exception.ApiException;
import com.BlogApplication.BlogApplicationProject.payloads.JwtAuthRequest;
import com.BlogApplication.BlogApplicationProject.payloads.JwtAuthResponse;
import com.BlogApplication.BlogApplicationProject.payloads.UserDto;
import com.BlogApplication.BlogApplicationProject.security.JwtTokenHelper;
import com.BlogApplication.BlogApplicationProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/loginPage")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            ) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(token);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception{
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException e){
            System.out.println("Invalid Details !!");
            throw new ApiException("Invalid username or password !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.registerNewUser(userDto);
        return  new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

}
