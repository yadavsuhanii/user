package com.user.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@RestController
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	
	}
	

	}


    // 	@Bean
    // public WebMvcConfigurer configure() {
    //    return  new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry reg) {
    //             reg.addMapping("/**").allowedOrigins("*");
    //         }
    //     };
    // }

	


 