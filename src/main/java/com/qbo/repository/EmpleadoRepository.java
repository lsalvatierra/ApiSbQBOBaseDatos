package com.qbo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qbo.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    
	@Transactional
	@Modifying
    @Query(value = "{call sp_RegistrarEmpleado(:nombre, :apellido)}", nativeQuery = true)
    void registrarEmpleado(
            @Param("nombre")String nombre,
            @Param("apellido")String apellido
            );
	
	@Query(value = "{call sp_AutenticarEmpleado(:usuario, :password)}", 
			nativeQuery = true)
	public Empleado autenticarEmpleado(
			@Param("usuario")String usuario,
			@Param("password")String password);
	
    
}
