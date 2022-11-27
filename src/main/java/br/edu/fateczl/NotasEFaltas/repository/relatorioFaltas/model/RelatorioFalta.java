package br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.model;

import java.math.BigInteger;
import java.util.List;

public class RelatorioFalta {
	String ra;
	String nome;
	String total;
	String disciplina;
	List<DataFalta> faltas;
	
	public String getRa() {
		return ra;
	}
	public void setRa(BigInteger ra) {
		this.ra = ra+"";
	}
	public void setRa(String ra) {
		this.ra = ra+"";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total+"";
	}
	public List<DataFalta> getFaltas() {
		return faltas;
	}
	public void setFaltas(List<DataFalta> faltas) {
		this.faltas = faltas;
	}
	
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	@Override
	public String toString() {
		return this.ra + " | " + this.nome + " | " + this.total;
	}
}
