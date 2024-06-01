package br.com.prolog.prolog_teste.security;

import br.com.prolog.prolog_teste.repository.ColaboradorRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
public class SecurityService {

  private final Environment env;

  private final ColaboradorRepository colaboradorRepository;

  public SecurityService(Environment env, ColaboradorRepository colaboradorRepository) {
    this.env = env;
    this.colaboradorRepository = colaboradorRepository;
  }

  public String generateToken(String email) {
    try {
      var algorithm = this.getAlgorithm();

      return JWT
          .create()
          .withIssuer("PROJECT-PROLOG")
          .withSubject(email)
          .withExpiresAt(this.getTokenExpirationTime())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException(exception);
    }
  }

  private Instant getTokenExpirationTime() {
    var expirationInterval = Integer.parseInt(Objects.requireNonNull(this.env.getProperty("security.token-expiration")));
    return LocalDateTime.now().plusHours(expirationInterval).toInstant(ZoneOffset.of("-03:00"));
  }

  private String getRequestToken(HttpServletRequest request) throws SecurityException {
    var authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      return authHeader.replaceAll("Bearer ", "");
    }

    return null;
  }

  private String getSubject(String token) throws SecurityException {
    var algorithm = this.getAlgorithm();

    return JWT
        .require(algorithm)
        .withIssuer("PROJECT-PROLOG")
        .build()
        .verify(token)
        .getSubject();
  }

  private Algorithm getAlgorithm() {
    return Algorithm.HMAC256(Objects.requireNonNull(env.getProperty("security.hmac-secret")));
  }

  public void authenticateRequest(HttpServletRequest request) throws SecurityException {
    var token = this.getRequestToken(request);
    if (token == null)
      return;

    var subject = this.getSubject(token);
    var user = this.colaboradorRepository.findByEmailAndStatusTrue(subject);
    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
