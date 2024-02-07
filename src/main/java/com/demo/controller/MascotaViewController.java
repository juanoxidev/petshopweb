package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.Duenio;
import com.demo.model.Mascota;
import com.demo.service.DuenioService;
import com.demo.service.MascotaService;

@Controller
public class MascotaViewController {

	@Autowired
	private DuenioService duenioService;

	@Autowired
	private MascotaService mascotaService;

	//pagina principal / home 
	@GetMapping("/")
	public String redirectToMascotas() {
		return "redirect:/mascotas";
	}
	
	@GetMapping("/mascotas")
	public String listarMascotas(Model model) {
		List<Mascota> mascotas = mascotaService.listarMascotas();
		model.addAttribute("mascotas", mascotas);
		return "mascotas";
	}
	
	@GetMapping("/agregarMascota")
	public String mostrarFormularioAgregarMascota(Model model) {
		model.addAttribute("duenios", duenioService.listarDuenios());
		model.addAttribute("mascota", new Mascota());
		return "agregarMascota";
	}
	
	@PostMapping("/guardarMascota")
	public String guardarMascota(@ModelAttribute Mascota mascota, @RequestParam Long idDuenio) {
		mascotaService.guardarMascota(mascota, idDuenio);
		return "redirect:/mascotas";	
	}
	
	
	@GetMapping("/eliminarMascota/{id}")
	public String eliminarMascota(@PathVariable Long id) {
		mascotaService.eliminarMascota(id);
		return "redirect:/mascotas";	
	}
	
	@GetMapping("/actualizarMascota/{id}")
	public String mostrarFormActualizarMascota(@PathVariable Long id, Model model) {
		model.addAttribute("mascota", mascotaService.obtenerMascotaPorId(id));
		model.addAttribute("duenios", duenioService.listarDuenios());
		return "actualizarMascota";	
	}
	
	@PostMapping("/actualizarMascota/{id}")
	public String actualizarMascota(@PathVariable Long id, @ModelAttribute Mascota mascotaActualizada, @RequestParam Long idDuenio) {
		mascotaActualizada.setDuenio(duenioService.obtenerDuenioPorId(idDuenio));
		mascotaService.actualizarMascota(id, mascotaActualizada);
		return "redirect:/mascotas";
	}
}
