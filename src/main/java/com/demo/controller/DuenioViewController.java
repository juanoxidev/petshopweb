package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.model.Duenio;
import com.demo.service.DuenioService;

@Controller
//@RequestMapping("/api/duenio")
public class DuenioViewController {

	@Autowired
	private DuenioService duenioService;
	
	@GetMapping("/duenios")
	private String listarDuenios(Model model) {
		List<Duenio> duenios = duenioService.listarDuenios();
		model.addAttribute("duenios", duenios);
		return "duenios";	
	}
	
	@GetMapping("/agregarDuenio")
	private String mostrarFormularioAgregarDuenio(Model model) {
		model.addAttribute("duenio", new Duenio());
		return "agregarDuenio";	
	}
	
	@PostMapping("/guardarDuenio")
	private String guardarDuenio(Duenio duenio) {
		duenioService.guardarDuenio(duenio);
		return "redirect:/duenios";	
	}
	
	
	
}
