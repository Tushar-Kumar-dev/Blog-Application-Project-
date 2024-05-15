package com.BlogApplication.BlogApplicationProject;

import com.BlogApplication.BlogApplicationProject.config.AppConstant;
import com.BlogApplication.BlogApplicationProject.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BlogApplicationProjectApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("xyz"));

		try{
			Role role = new Role();
			role.setId(AppConstant.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role.setId(AppConstant.NORMAL_USER);
			role.setName("ROLE_USER");

			List<Role> result = List.of(role, role1);

			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
