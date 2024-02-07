package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.model.Usuario;
import com.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario encontrarUsuarioPorNombre(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		return usuarioRepository.save(usuario);
	}
}
