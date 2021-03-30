package com.example.demo.controller;

import com.example.demo.exception.BadDataException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.models.requests.Login;
import com.example.demo.models.requests.RegistrationUser;
import com.example.demo.models.response.Token;
import com.example.demo.models.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Service;
import com.example.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenController {
    private final Service service;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;

    public OpenController(Service service, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository, UserService userService) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    //@RequestMapping(value = "/api/v1/open/chat",method = RequestMethod.POST)
    //public List<Message> getsendMessages( @RequestBody SendRequest sendRequest){
  //  }

    @RequestMapping(value = "/api/v1/open/registration",method = RequestMethod.POST)
    public User registration(@Valid @RequestBody RegistrationUser registration){
       return service.registration(registration);
    }

    @RequestMapping(value = "api/v1/open/login",method = RequestMethod.POST)
    public Token Login(@Valid @RequestBody Login login){
        User user=(User)userService.loadUserByUsername(login.getEmail());
        if(user==null){
            throw new BadDataException("password or username is not correct");
        }
        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
            throw new BadDataException("password or username is not correct");
        }
        List<String> roles=new ArrayList<>();
        roles.add(user.getRole());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),login.getPassword(),getGrantedAuthorities(roles));
        authenticationManager.authenticate(authentication);
        String token = jwtTokenProvider.createToken(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token1=new Token(token);
        userRepository.save(user);
        return token1;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}