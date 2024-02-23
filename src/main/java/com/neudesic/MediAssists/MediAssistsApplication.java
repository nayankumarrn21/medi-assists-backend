package com.neudesic.MediAssists;

import com.neudesic.MediAssists.dto.UserDto;
import com.neudesic.MediAssists.mappers.UserMapper;
import com.neudesic.MediAssists.modules.User;
import com.neudesic.MediAssists.services.UserService;
import jakarta.annotation.PostConstruct;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class MediAssistsApplication {


	@Bean
	public UserMapper userMapper() {
		return Mappers.getMapper(UserMapper.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MediAssistsApplication.class, args);
	}


}
