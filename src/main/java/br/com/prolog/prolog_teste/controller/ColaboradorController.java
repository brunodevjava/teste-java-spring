package br.com.prolog.prolog_teste.controller;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.dto.colaborador.UserChangePasswordDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserDetailDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserRegisterDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserUpdateDTO;
import br.com.prolog.prolog_teste.repository.ColaboradorRepository;
import br.com.prolog.prolog_teste.services.ColaboradorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @Autowired
    private ColaboradorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDetailDTO> create(
            @RequestBody @Valid UserRegisterDTO userRegisterDTO,
            UriComponentsBuilder uriBuilder
    ) {
        var user = this.service.create(userRegisterDTO);
        var uri = uriBuilder.path("/colaborador/{id}").buildAndExpand(user.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailDTO(user));
    }

    @PutMapping("/{cpf}")
    @Transactional
    public ResponseEntity<UserDetailDTO> updateColaborador(@PathVariable String cpf, @Validated @RequestBody UserUpdateDTO userUpdateDTO) {
        Colaborador colaborador = service.update(cpf, userUpdateDTO);
        return new ResponseEntity<>(new UserDetailDTO(colaborador.getCpf(),colaborador.getNome(),colaborador.getEmail()), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserDetailDTO> retrieve(@PathVariable("cpf") String cpf) {
        var user = this.service.getColaborador(cpf);

        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailDTO>> list(
            @PageableDefault(sort = {"cpf"}, size = 100) Pageable pageable
    ) {
        var users = this.service.getColaboradores(pageable).map(UserDetailDTO::new);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/inativos")
    public ResponseEntity<Page<UserDetailDTO>> listInativos(
            @PageableDefault(sort = {"cpf"}, size = 100) Pageable pageable
    ) {
        var users = this.service.getColaboradoresInativos(pageable).map(UserDetailDTO::new);

        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<?> delete(
            @PathVariable("cpf") String cpf    ) {
        this.service.delete(cpf);
        return new ResponseEntity<>("Colaborador deletado com sucesso! ", HttpStatus.OK);
    }

    @PatchMapping("/alterar-senha")
    @Transactional
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid UserChangePasswordDTO userChangePasswordDTO,
            Authentication auth
    ) {
        var colaborador = (Colaborador) auth.getPrincipal();
        this.service.changePassword(userChangePasswordDTO);
        return new ResponseEntity<>("Senha do colaborador "+colaborador.getNome()+ " alterada com sucesso!", HttpStatus.OK);
    }

}
