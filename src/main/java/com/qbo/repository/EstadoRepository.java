package com.qbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qbo.model.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	/*
	 * El patrón repositorio consiste en separar la lógica 
	 * que recupera los datos y los asigna a un modelo de entidad 
	 * de la lógica de negocios que actúa sobre el modelo, 
	 * esto permite que la lógica de negocios sea independiente del tipo de 
	 * dato que comprende la capa de origen de datos*/
}
