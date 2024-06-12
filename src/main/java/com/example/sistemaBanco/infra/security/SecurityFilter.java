package com.example.sistemaBanco.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sistemaBanco.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //faz com que a classe seja um bean gerenciado pelo Spring, permite sua injeção em outros componentes
public class SecurityFilter extends OncePerRequestFilter {

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
            UserDetails usuario = usuarioRepository.findByEmail(email); // busca o email do usuário

            //instância de UsernamePasswordAuthenticationToken é criada com as informações do usuário e definida no contexto de segurança 
            //SecurityContextHolder estabelecendo que o usuário está autenticado
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // requisição é passada para o próximo filtro na cadeia de filtros
    }

    // extrai o token do cabeçalho "Authorization" da requisição http
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null; // se o cabeçalho não tiver, retorna null
        return authHeader.replace("Bearer ", ""); // se tiver, remove o prefixp "Bearer " para pegar o token real
    }
}

