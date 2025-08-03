package com.financeiro.api.controller;

import com.financeiro.api.dto.CreateUser;
import com.financeiro.api.dto.LoginUser;
import com.financeiro.api.dto.RecoveryJwtToken;
import com.financeiro.api.service.AutenticacaoService;
import com.financeiro.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<RecoveryJwtToken> authenticateUser(@RequestBody LoginUser loginUserDto) {
        RecoveryJwtToken token = autenticacaoService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<Void> createUser(@RequestBody CreateUser createUserDto) {
        usuarioService.createUser(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
