package com.neudesic.MediAssists.services;

import com.neudesic.MediAssists.dto.AuthenticationRequest;
import com.neudesic.MediAssists.dto.AuthenticationResponse;
import com.neudesic.MediAssists.dto.UserDto;
import com.neudesic.MediAssists.exceptions.PasswordNotMatchException;
import com.neudesic.MediAssists.exceptions.ResourceNotFoundException;
import com.neudesic.MediAssists.jwt.JwtUtil;
import com.neudesic.MediAssists.mappers.UserMapper;
import com.neudesic.MediAssists.modules.User;
import com.neudesic.MediAssists.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final UserMapper userMapper;
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not found");
        }

        String[] rolesArray = user.getRole().toString().split(",");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : rolesArray) {
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);

    }


    public User createUser(UserDto userDto){
       User user =  userMapper.userDTOToUser(userDto);
       return userRepository.save(user);
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        User user = userRepository.findByUserName(request.getUsername());
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        if(request.getPassword().equals(user.getPassword())){
            AuthenticationResponse response = new AuthenticationResponse();
            response.setToken(jwtUtil.generateToken(request.getUsername()));
            response.setUser(user);
            return response;
        }else {
            throw new PasswordNotMatchException("Password did not match");
        }
    }

    public User getUserId(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User user){
        User userOG = getUserId(id);
        if(userOG != null ){
            if(user.getPassword()==null || user.getPassword().isEmpty()){
                user.setPassword(userOG.getPassword());
            }
           return userRepository.save(user);
        }
        throw new ResourceNotFoundException("User","id",id);
    }

    @PostConstruct
    public void postConstruct(){
        UserDto user = new UserDto();
        user.setUserName("admin@gmail.com");
        user.setGender("Male");
        user.setFullName("Admin");
        user.setPhNumber("9990909090");
        user.setRole("admin");
        user.setWorkType("Doctor");
        user.setPassword("21");
        user.setDob(LocalDate.now().minusDays(10));
        createUser(user);
    }

}
