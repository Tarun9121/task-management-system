package com.task.task.management.system.controller;

import com.task.task.management.system.dto.LoginDto;
import com.task.task.management.system.service.implementation.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/")
    public ResponseEntity<String> checkUserCrediantals(@RequestBody LoginDto loginDto) {
        return loginService.checkUserCrediantals(loginDto);
    }
}
