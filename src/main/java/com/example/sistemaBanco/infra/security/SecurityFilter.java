package com.example.sistemaBanco.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sistemaBanco.dto.response.ResponseUsuarioDetails;
import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //faz com que a classe seja um bean gerenciado pelo Spring, permite sua injeção em outros componentes
public class SecurityFilter extends OncePerRequestFilter {
// ela extends o OncePerRequestFilter que é um filtro que acontece uma vez a cada requisiçãi 
	// precisa ser gerado um método que é o doFilterInternal que vai ser chamado antes
	@Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    // a cada requisição recebida, chama esse método
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request); //O recoverToken extrai o token do cabeçalho "Authorization" da requisição
        if(token != null){ // se o token for diferente de nulo
            var email = tokenService.validateToken(token); // se for encontrado um token ele é validade no service
            // se validar com sucesso chama o UserDetails para encontrar o usuario 
            Usuario usuario = usuarioRepository.findByEmail(email); 
            //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
           
            ResponseUsuarioDetails usuarioDetails = ResponseUsuarioDetails.fromUsuario((Usuario) usuario); 
            var authentication = new UsernamePasswordAuthenticationToken(usuarioDetails, null, usuarioDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //SecurityContextHolder(é o contexto da autenticaçaõ)  estabelecendo que o usuário está autenticado
            // esse context é o contexto que o sprint ja sabe trabalhar e eu passo passo para ele as informações que ele precis.
            //mas esse momento é quando o usuario ja fez a autenticação 
        }
        filterChain.doFilter(request, response); // se n tiver token chama o proximo filtro que é o username
    }

    // extrai o token do cabeçalho "Authorization" da requisição http
    private String recoverToken(HttpServletRequest request){
    	// dentro do request temos os header, um deles é o authorization que é onde o usuario vai passar o token
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) 
        	return null; // retorna null se n tiver nenhum token
        return authHeader.replace("Bearer ", ""); // se tiver, remove o prefixp "Bearer " e o espaço para pegar o token real
    }
}

