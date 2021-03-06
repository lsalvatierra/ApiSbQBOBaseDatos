package com.qbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="estado")
public class Estado   {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Long idestado;
	
	@Column(name="descestado")
    private String descestado;

	public Long getIdestado() {
		return idestado;
	}

	public void setIdestado(Long idestado) {
		this.idestado = idestado;
	}

	public String getDescestado() {
		return descestado;
	}

	public void setDescestado(String descestado) {
		this.descestado = descestado;
	}

	public Estado(String descestado) {
		super();
		this.descestado = descestado;
	}

	public Estado() {
		super();
	}
	
	
	
}
