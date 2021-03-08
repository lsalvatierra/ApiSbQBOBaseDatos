package com.qbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idcliente;	
	@Column(name = "nomcliente")
	private String nomcliente;
	@Column(name = "apecliente")
	private String apecliente;
	@Column(name = "dnicliente")
	private String dnicliente;
	public Long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}
	public String getNomcliente() {
		return nomcliente;
	}
	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}
	public String getApecliente() {
		return apecliente;
	}
	public void setApecliente(String apecliente) {
		this.apecliente = apecliente;
	}
	public String getDnicliente() {
		return dnicliente;
	}
	public void setDnicliente(String dnicliente) {
		this.dnicliente = dnicliente;
	}
	
	

}
