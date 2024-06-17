package com.example.sistemaBanco.dto.response;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.sistemaBanco.entities.Usuario;
import com.example.sistemaBanco.entities.enums.TipoUsuario;

public class ResponseUsuarioDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    public ResponseUsuarioDetails(Long id, String nomeCompleto, String email, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public static ResponseUsuarioDetails fromUsuario(Usuario usuario) {
        return new ResponseUsuarioDetails(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipo()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	if(this.tipo == TipoUsuario.COMUM)
			return List.of(new SimpleGrantedAuthority("ROLE_COMUM"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_LOJISTA"));

    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

}
