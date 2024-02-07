package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.model.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	// metodo utilizado para cuestiones de logeo
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.encontrarUsuarioPorNombre(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException(" Usuario no encontrado : " + username);
		}
		
		return User.withUsername(usuario.getNombre()).password("{bcrypt}"+usuario.getContrasenia()).build();
	}

/*	Ejemplo de usuario con contrasenia harcodeada(en texto plano) cifrado {noop} : es sin cifrado. {bcrypt} : es con cifrado 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("user".equals(username)) {
			return User.withUsername("user").password("{noop}pass").roles("USER").build();

		} else {
			throw new UsernameNotFoundException("Usuario no encontrado " + username);
		}
	}
*/
}
