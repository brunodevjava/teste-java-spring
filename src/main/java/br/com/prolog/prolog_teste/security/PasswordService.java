package br.com.prolog.prolog_teste.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

  private final PasswordEncoder passwordEncoder;

  public PasswordService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public String encodePassword(String senha) {
    return this.passwordEncoder.encode(senha);
  }

  public Boolean comparePassword(String senha, String encodedPassword) {
    return this.passwordEncoder.matches(senha, encodedPassword);
  }
}
