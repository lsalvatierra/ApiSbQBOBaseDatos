package com.qbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.model.Empleado;
import com.qbo.service.EmpleadoService;


@RestController
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController {
	@Autowired
	protected EmpleadoService servicio;

	@PostMapping("")
	public ResponseEntity<?> createTutorial(@RequestBody Empleado empleado) {
		return new ResponseEntity<>(servicio.registrarEmpleado(new Empleado(empleado.getNombre(), empleado.getApellido())), HttpStatus.CREATED);
	}
	
	
}
