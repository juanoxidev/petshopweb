package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Mascota;
@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
	Optional<Mascota> findByNombre(String nombre);
	// distingue entre minusculas y mayusculas
	List<Mascota> findAllByOrderByNombreAsc() ;
}
