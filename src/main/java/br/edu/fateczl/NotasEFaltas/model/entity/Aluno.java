package br.edu.fateczl.NotasEFaltas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.fateczl.NotasEFaltas.model.dto.AlunoDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.interfaces.IEntity;

@Entity
@Table(name = "tbAluno")
public class Aluno implements IEntity<AlunoDTO>{
	@Id
	@Column(name = "ra",nullable = false)
	private Long RA;
	@Column(name = "nome",length = 80,nullable = false)
	private String nome;
	
	public long getRA() {
		return RA;
	}
	public void setRA(long rA) {
		RA = rA;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public AlunoDTO toDTO() {
		AlunoDTO a = new AlunoDTO();
		a.setNome(this.nome);
		a.setRA(this.RA);		
		return a;
	}	
}
