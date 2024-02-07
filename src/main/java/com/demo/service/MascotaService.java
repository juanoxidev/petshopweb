package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.model.Duenio;
import com.demo.model.Mascota;
import com.demo.repository.DuenioRepository;
import com.demo.repository.MascotaRepository;

@Service
public class MascotaService {

	@Autowired
	private MascotaRepository mascotaRepository;
	
	@Autowired
	private DuenioRepository duenioRepository;
	
	public Mascota obtenerMascotaPorId(Long id) {
		return mascotaRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro la mascota"));
	}
	public List<Mascota> listarMascotas(){
		//criterio
		Sort sortBy = Sort.by(Sort.Order.asc("nombre").ignoreCase());
		// retonamos segun repository y criterio
		return mascotaRepository.findAll(sortBy);
	}
	
	public Mascota guardarMascota (Mascota mascota, Long idDuenio) {
		Duenio duenio = duenioRepository.findById(idDuenio).orElseThrow(() -> new RuntimeException("No se encontro al duenio"));
		mascota.setDuenio(duenio);
		mascotaRepository.save(mascota);
		return mascota;
	}
	
	public void eliminarMascota(Long id) {
		Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
		if (mascotaOptional.isPresent()) {
			Mascota mascotaExistente = mascotaOptional.get();
			mascotaRepository.delete(mascotaExistente);
		} else {
			throw new RuntimeException("Mascota no encontrada al momento de la eliminacion");
		}
	}
	
	public void actualizarMascota(Long id, Mascota mascotaActualizada) {
		Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
		if (mascotaOptional.isPresent()) {
			Mascota mascotaExistente = mascotaOptional.get();
			mascotaExistente.setDuenio(mascotaActualizada.getDuenio());
			mascotaExistente.setEdad(mascotaActualizada.getEdad());
			mascotaExistente.setEspecie(mascotaActualizada.getEspecie());
			mascotaExistente.setNombre(mascotaActualizada.getNombre());
			
			mascotaRepository.save(mascotaExistente);
		} else {
			throw new RuntimeException("Mascota no encontrada al momento de la actualizacion");
		}
	}

}
