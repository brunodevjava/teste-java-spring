package br.com.prolog.prolog_teste.services;

import br.com.prolog.prolog_teste.domain.Colaborador;
import br.com.prolog.prolog_teste.domain.Log;
import br.com.prolog.prolog_teste.dto.LogDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserChangePasswordDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserRegisterDTO;
import br.com.prolog.prolog_teste.dto.colaborador.UserUpdateDTO;
import br.com.prolog.prolog_teste.error.RequestFieldException;
import br.com.prolog.prolog_teste.repository.ColaboradorRepository;
import br.com.prolog.prolog_teste.repository.LogRepository;
import br.com.prolog.prolog_teste.security.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class ColaboradorService {

    private static final Logger logger = LoggerFactory.getLogger(ColaboradorService.class);


    private final ColaboradorRepository repository;
    private final PasswordService passwordService;
    private final LogService logService;

    public ColaboradorService(ColaboradorRepository repository, PasswordService passwordService, LogService logService) {
        this.repository = repository;
        this.passwordService = passwordService;
        this.logService = logService;
    }


    public Colaborador create(UserRegisterDTO userRegisterDTO) throws RequestFieldException {
        if (this.validateUniqueConstraint(userRegisterDTO.email())) {
            throw new RequestFieldException("O email enviado já está registrado na nossa base de dados", "email");
        }
        var colaborador = new Colaborador(userRegisterDTO);
        colaborador.setPassword(this.passwordService.encodePassword(userRegisterDTO.senha()));

        this.repository.save(colaborador);
       LogDTO logDTO = new LogDTO(Log.LogAction.CREATE, "COLABORADOR", colaborador);
        logService.create(logDTO);
        logger.info("Colaborador criado com sucesso: {}", colaborador);

        return colaborador;
    }

    public Colaborador update(String cpf, UserUpdateDTO userUpdateDTO) throws RequestFieldException {
        var colaboradorOptional = this.repository.findById(cpf);

        if (colaboradorOptional.isPresent()) {
            var colaborador = colaboradorOptional.get();
            colaborador.setNome(userUpdateDTO.nome());
            colaborador.setEmail(userUpdateDTO.email());
            // Atualize outros campos conforme necessário

            this.repository.save(colaborador);
            logService.create(new LogDTO(Log.LogAction.UPDATE, "COLABORADOR", colaborador));

            logger.info("Colaborador atualizado com sucesso: {}", colaborador);

            return colaborador;
        } else {
            throw new RequestFieldException("Colaborador não encontrado", "COLABORADOR");
        }
    }


    public void delete(String cpf) {
        var colaborador = this.repository.findById(cpf);

        if (colaborador.isPresent()) {
            logService.create(new LogDTO(Log.LogAction.DELETE, "COLABORADOR", this.repository.findByCpf(cpf)));
            logger.info("Colaborador deletado com sucesso: {}", colaborador);

            this.repository.delete(cpf);
        } else {
            throw new RequestFieldException("Colaborador não existe", "COLABORADOR");
        }

    }

    private boolean validateUniqueConstraint(String email) {
        return this.repository.emailExists(email);
    }

    public Colaborador getColaborador(String cpf) {
        var foundUser = this.repository.findById(cpf);
        return foundUser.get();
    }

    public Colaborador getColaboradorByEmail(String email) {
        return this.repository.findByEmailAndStatusTrue(email);
    }

    public Page<Colaborador> getColaboradores(Pageable pageable) {
        return this.repository.findAllByStatusTrue(pageable);
    }

    public Page<Colaborador> getColaboradoresInativos(Pageable pageable) {
        return this.repository.findAllByStatusFalse(pageable);
    }


    public Boolean userExists(String cpf) {
        return this.getColaborador(cpf) != null;
    }

    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) {
        var colaborador = this.getColaborador(userChangePasswordDTO.cpf());

        if (!this.passwordService.comparePassword(userChangePasswordDTO.senhaAntiga(), colaborador.getPassword())) {
            throw new RequestFieldException("A senha atual não é válida", "senhaAtual");
        }

        colaborador.setPassword(this.passwordService.encodePassword(userChangePasswordDTO.novaSenha()));

        this.repository.save(colaborador);

        logger.info("Senha alterada com sucesso: {}", colaborador.getPassword());

    }

}