package com.qbo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empleado {
	
	@Id
    private Long idempleado;
    private String nombre;
    private String apellido;

	public Long getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(Long idempleado) {
		this.idempleado = idempleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	
	public Empleado(Long idempleado, String nombre, String apellido) {
		super();
		this.idempleado = idempleado;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Empleado(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Empleado() {
		super();
		
	}
	



}
