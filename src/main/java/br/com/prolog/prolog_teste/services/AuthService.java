package br.com.prolog.prolog_teste.services;

import br.com.prolog.prolog_teste.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

  private final ColaboradorRepository repository;

  public AuthService(ColaboradorRepository repository) {
    this.repository = repository;
  }

  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmailAndStatusTrue(email);
  }
}
