package br.edu.fateczl.NotasEFaltas.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FaltaID implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "raAluno",nullable = false)
	private Long RA;
	@Column(name = "codigoDisciplina",nullable = false)
	private Long codigo;
	@Column(name = "data",nullable = false)
	private LocalDate data;
	
	public long getRA() {
		return RA;
	}
	public void setRA(long rA) {
		RA = rA;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
}
