package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbo.model.*;
import com.qbo.repository.EstadoRepository;

@Service
public class EstadoService implements BaseService<Estado> {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> findAll() {
		List<Estado> entities = estadoRepository.findAll();
		return entities;
	}

	@Override
	public Optional<Estado> findById(Long id) {
		Optional<Estado> entityOptional = estadoRepository.findById(id);
		if(entityOptional.isEmpty()) {
			return Optional.empty();
		}else {
			return entityOptional;				
		}
		
	}

	@Override
	public Estado save(Estado entity) {
		entity = estadoRepository.save(entity);
		return entity;
	}

	@Override
	public Estado update(Long id, Estado entity) {
		Optional<Estado> entityOptional = estadoRepository.findById(id);
		Estado entityUpdate = entityOptional.get();
		entityUpdate = estadoRepository.save(entity);
		return entityUpdate;
	}

	@Override
	public HashMap<String, String> deleteById(Long id) {
		HashMap<String, String> respuesta = new HashMap<String, String>();
		estadoRepository.deleteById(id);
		respuesta.put("mensaje", "Elemento eliminado correctamente");
		return respuesta;
	}
	
	

	
}
