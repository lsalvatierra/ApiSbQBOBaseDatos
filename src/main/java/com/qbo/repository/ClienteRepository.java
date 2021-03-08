package com.qbo.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qbo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	/*@Query(value = "SELECT c FROM cliente c WHERE c.dnicliente = :dnicliente")
	List<Cliente> searchByDniJPAQuery(@Param("dnicliente")String dniCliente);
	*/
	@Query(
			value = "SELECT * FROM cliente WHERE cliente.dnicliente = :dnicliente",
			nativeQuery = true)
	Optional<Cliente> searchByDniQueryNative(@Param("dnicliente")String dniCliente);

	@Query(
			value = "SELECT * FROM cliente WHERE cliente.nomcliente LIKE %:nomcliente%",
			countQuery = "SELECT count(*) FROM cliente",
			nativeQuery = true)
	Page<Cliente> searchByNombreQueryNative(
			@Param("nomcliente")String nomcliente,
			Pageable pageable);

}
