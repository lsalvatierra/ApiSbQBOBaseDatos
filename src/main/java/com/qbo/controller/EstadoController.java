package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.exception.ResourceNotFoundException;
import com.qbo.model.Estado;
import com.qbo.service.*;

@RestController
@RequestMapping(path = "api/v1/estado")
public class EstadoController {

	@Autowired
	protected EstadoService servicio;

	@GetMapping("")
	public ResponseEntity<List<Estado>> getAll() {
		List<Estado> estados = new ArrayList<Estado>();
		servicio.findAll().forEach(estados::add);
		if (estados.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(estados, HttpStatus.OK);
	}

	  @GetMapping("/{id}")
	  public ResponseEntity<Estado> getTutorialById(@PathVariable("id") long id) {
		  Estado tutorial = servicio.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
	    return new ResponseEntity<>(tutorial, HttpStatus.OK);
	  }

	  @PostMapping("")
	  public ResponseEntity<Estado> createTutorial(@RequestBody Estado estado) {
		Estado _tutorial = servicio.save(new Estado(estado.getDescestado()));
	    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	  }

	  @PutMapping("/{id}")
	  public ResponseEntity<Estado> updateTutorial(@PathVariable("id") long id, @RequestBody Estado estado) {
		  Estado _estado = servicio.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
		  _estado.setDescestado(estado.getDescestado());
	    return new ResponseEntity<>(servicio.save(_estado), HttpStatus.OK);
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
		servicio.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));		  
	    return ResponseEntity.status(HttpStatus.OK).body(servicio.deleteById(id));
	  }


	
	
}
