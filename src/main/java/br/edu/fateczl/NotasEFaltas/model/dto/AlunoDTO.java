package br.edu.fateczl.NotasEFaltas.model.dto;

import br.edu.fateczl.NotasEFaltas.model.dto.interfaces.IDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Aluno;

public class AlunoDTO implements IDTO<Aluno>{
	private Long RA;
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
	public Aluno toEntity() {
		Aluno a = new Aluno();
		a.setNome(this.nome);
		a.setRA(this.RA);
		return a;
	}
}
