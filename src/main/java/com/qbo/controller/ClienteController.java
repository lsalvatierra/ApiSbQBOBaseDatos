package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.exception.ResourceNotFoundException;
import com.qbo.model.Cliente;
import com.qbo.service.ClienteService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/cliente")
public class ClienteController {

	@Autowired
	protected ClienteService servicio;

	@GetMapping("")
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		servicio.findAll().forEach(clientes::add);
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<Cliente> searchByDni(@PathVariable("dni") String dni) {
		Cliente cliente = servicio.searchByDni(dni)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with DNI = " + dni));
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<?> searchByNombre(@RequestParam String nombre, Pageable pageable) {
		Page<Cliente> clientes = servicio.searchByNombre(nombre, pageable);
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	 @PreAuthorize("hasRole('USER')")
	  @GetMapping("/{id}")
	  public ResponseEntity<Cliente> getTutorialById(@PathVariable("id") long id) {
		  Cliente cliente = servicio.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
	    return new ResponseEntity<>(cliente, HttpStatus.OK);
	  }

	  @PostMapping("")
	  public ResponseEntity<Cliente> createTutorial(@RequestBody Cliente cliente) {
		  Cliente _cliente = servicio.save(cliente);
	    return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Cliente> updateTutorial(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		  Cliente _cliente = servicio.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
		_cliente.setNomcliente(cliente.getNomcliente());
		_cliente.setApecliente(cliente.getApecliente());
		_cliente.setDnicliente(cliente.getDnicliente());
	    return new ResponseEntity<>(servicio.save(_cliente), HttpStatus.OK);
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
		servicio.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));		  
	    return ResponseEntity.status(HttpStatus.OK).body(servicio.deleteById(id));
	  }

	
}
