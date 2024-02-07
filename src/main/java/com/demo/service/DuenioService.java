package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Duenio;
import com.demo.repository.DuenioRepository;

@Service
public class DuenioService {

	@Autowired
	private DuenioRepository duenioRepository;
	
	public Duenio obtenerDuenioPorId(Long id) {
		return duenioRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el duenio"));
	}
	
	public List<Duenio> listarDuenios(){
		return duenioRepository.findAll();
	}
	
	public Duenio guardarDuenio (Duenio duenio) {
		return duenioRepository.save(duenio);
	}
	
	
}
