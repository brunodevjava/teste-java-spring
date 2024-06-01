package br.com.prolog.prolog_teste.controller;

import br.com.prolog.prolog_teste.dto.colaborador.PasswordRecoveryKeyDTO;
import br.com.prolog.prolog_teste.dto.colaborador.PasswordRecoveryRecoverDTO;
import br.com.prolog.prolog_teste.services.RecuperacaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/recuperar-senha")
public class RecuperarSenhaController {

    private final RecuperacaoSenhaService service;

    public RecuperarSenhaController(RecuperacaoSenhaService service) {
        this.service = service;
    }

    @PostMapping("/gerar-chave/{cpf}")
    public ResponseEntity<PasswordRecoveryKeyDTO> inicioRecuperacao(
            @PathVariable("cpf") String cpf,
            UriComponentsBuilder uriBuilder
    ) {
        var recoveryKey = this.service.init(cpf);

        var uri = uriBuilder.path("/colaborador/{cpf}/recuperar-senha?chaveRecuperacao={chave}").buildAndExpand(cpf, recoveryKey).toUri();

        return ResponseEntity.created(uri).body(new PasswordRecoveryKeyDTO(recoveryKey));
    }

    @PatchMapping("/recuperacao")
    public ResponseEntity<?> recuperar(
            @RequestBody PasswordRecoveryRecoverDTO passwordRecoveryRecoverDTO
    ) {
        this.service.recover(passwordRecoveryRecoverDTO);

        return ResponseEntity.noContent().build();
    }

}
