package com.alfonsovidrio.forohub.controller;

import com.alfonsovidrio.forohub.domain.user.User;
import com.alfonsovidrio.forohub.domain.user.UserAuthenticationData;
import com.alfonsovidrio.forohub.infra.security.TokenService;
import com.alfonsovidrio.forohub.infra.security.JwtTokenData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody UserAuthenticationData userAuthenticationData) {
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(
                userAuthenticationData.name(),
                userAuthenticationData.password()
        );
        var authenticatedUser = authenticationManager.authenticate(AuthToken);
        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JwtTokenData(jwtToken));
    }

}
