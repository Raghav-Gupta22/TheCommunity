package com.nagarro.exittest.controllers;

import com.nagarro.exittest.config.JwtService;
import com.nagarro.exittest.impl.UserDetailsServiceImpl;
import com.nagarro.exittest.models.JwtRequest;
import com.nagarro.exittest.models.JwtResponse;
import com.nagarro.exittest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl detailsServiceImpl;

    @Autowired
    private JwtService jwtService;

    /**
     * @param jwtRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/generate-token")
    @CrossOrigin("*")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found");
        }
        UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
        System.out.println(userDetails);
        String s = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(s));
    }

    /**
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Disable");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials!!");
        }
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping("/current-user")
    @CrossOrigin("*")
    public User getCurrentUser(Principal principal) {
        System.out.println(principal.getName());
        return ((User) this.detailsServiceImpl.loadUserByUsername(principal.getName()));
    }

}
