package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.model.Usuario;
import com.demo.service.UsuarioService;

@Controller
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";

	}

	@PostMapping("/registro")
	public String registrarUsuario(Usuario usuario) {
		usuarioService.guardarUsuario(usuario);
		return "redirect:/login";
	}

}
