package br.com.prolog.prolog_teste.controller;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.dto.colaborador.TokenDetailDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserAuthDTO;
import br.com.prolog.prolog_teste.security.SecurityService;
import br.com.prolog.prolog_teste.services.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authManager;

    private final SecurityService securityService;

    private final ColaboradorService colaboradorService;

    public LoginController(AuthenticationManager authManager, SecurityService securityService, ColaboradorService colaboradorService) {
        this.authManager = authManager;
        this.securityService = securityService;
        this.colaboradorService = colaboradorService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthDTO authDTO) {
        try {
            var token = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
            var auth = this.authManager.authenticate(token);
            var userToken = this.securityService.generateToken(((Colaborador) auth.getPrincipal()).getEmail());
            var colaborador = this.colaboradorService.getColaboradorByEmail(authDTO.email());

            return ResponseEntity.ok(new TokenDetailDTO(userToken, colaborador.getCpf(), colaborador.getNome(), colaborador.getEmail()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication required.");
        }
    }

}
