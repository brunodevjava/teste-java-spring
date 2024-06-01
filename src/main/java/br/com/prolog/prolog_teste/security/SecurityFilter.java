package br.com.prolog.prolog_teste.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private SecurityService securityService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    try {
      this.securityService.authenticateRequest(request);
      liberacaoCors(response); // Liberar CORS antes de continuar
      filterChain.doFilter(request, response);
    } catch (SecurityException e) {
      handleSecurityException(response, e);
    } catch (Exception e) {
      System.out.println("ERROR: "+e.getMessage());
      handleGenericException(response, e);
    }
  }

  private void handleSecurityException(HttpServletResponse response, SecurityException e) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(e.getMessage());
  }

  private void handleGenericException(HttpServletResponse response, Exception e) throws IOException {
    response.setStatus(500);
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setContentType("application/json");
    response.getWriter().append(json(e.getMessage()));
    System.out.println("ERRO: "+e.getMessage());
  }

  private void liberacaoCors(HttpServletResponse response) {

    if (response.getHeader("Access-Control-Allow-Origin") == null) {
      response.addHeader("Access-Control-Allow-Origin", "*");
    }

    if (response.getHeader("Access-Control-Allow-Headers") == null) {
      response.setHeader("Access-Control-Allow-Headers", "*");
    }


    if (response.getHeader("Access-Control-Request-Headers") == null) {
      response.addHeader("Access-Control-Request-Headers", "*");
    }

    if (response.getHeader("Access-Control-Allow-Methods") == null) {
      response.setHeader("Access-Control-Allow-Methods", "*");
    }
    if (response.getHeader("Access-Control-Allow-Credentials") == null) {
      response.setHeader("Access-Control-Allow-Credentials", "true");
    }

  }
  private String json(String message) {
    long date = new Date().getTime();
    return "{\"timestamp\": " + date + ","
            + "\"status\": 401, "
            + "\"error\": \"Unauthorized\", "
            + "\"message\": \"" + message.replace("\"", " ") + "\", "
            + "\"path\": \"/login\"}";
  }

}
