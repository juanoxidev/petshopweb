package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.model.Duenio;
import com.demo.service.DuenioService;


@SpringBootTest
public class DuenioServiceTest {

	@Autowired
	private DuenioService duenioService;
	
	@Test
	public void testGuardarDuenio() {
		Duenio duenio = new Duenio();
		duenio.setNombre("Pepe");
		duenio.setDireccion("Av ggg 222");
		
		Duenio duenioGuardado = duenioService.guardarDuenio(duenio);
		
        assertNotNull(duenioGuardado.getId());
        assertEquals("Pepe",duenioGuardado.getNombre());
        assertEquals("Av ggg 222", duenioGuardado.getDireccion());
	}
	
    @Test
    public void testListarDuenios() {
        List<Duenio> duenios = duenioService.listarDuenios();
        assertTrue(duenios.size() > 0);
    }

    @Test
    public void testObtenerDuenioPorId(){
        Long duenioId = 1L;
        Duenio duenio = duenioService.obtenerDuenioPorId(duenioId);

        assertNotNull(duenio);
        assertEquals(duenioId, duenio.getId());
    }

}
