package com.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;
	@NotBlank(message = "La especie no puede estar en blanco")
	private String especie;
	
	@Min(value = 0, message = "El número debe ser mayor o igual a 0")
	@Max(value = 20, message = "El número no puede ser mayor a 20")
	private int edad;
	
	@ManyToOne
	private Duenio duenio;
}
