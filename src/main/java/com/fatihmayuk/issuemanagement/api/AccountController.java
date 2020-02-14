package com.fatihmayuk.issuemanagement.api;

import com.fatihmayuk.issuemanagement.dto.LoginRequestDto;
import com.fatihmayuk.issuemanagement.dto.RegistrationRequestDto;
import com.fatihmayuk.issuemanagement.dto.TokenReponseDto;
import com.fatihmayuk.issuemanagement.entity.User;
import com.fatihmayuk.issuemanagement.repository.UserRepository;
import com.fatihmayuk.issuemanagement.security.JwtTokenUtil;
import com.fatihmayuk.issuemanagement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/token")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenReponseDto> login(@RequestBody LoginRequestDto request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenReponseDto(user.getUsername(),token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequestDto registrationRequest) throws AuthenticationException {
        Boolean response = userServiceImpl.register(registrationRequest);
        return ResponseEntity.ok(response);
    }



}
