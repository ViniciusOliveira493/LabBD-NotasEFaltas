package br.edu.fateczl.NotasEFaltas.model.dto;

import br.edu.fateczl.NotasEFaltas.model.dto.interfaces.IDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Disciplina;

public class DisciplinaDTO implements IDTO<Disciplina>{
	private Long codigo;
	private String nome;
	private String sigla;
	private String turno;
	private Integer num_aulas;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getNum_aulas() {
		return num_aulas;
	}
	public void setNum_aulas(int num_aulas) {
		this.num_aulas = num_aulas;
	}
	@Override
	public Disciplina toEntity() {
		Disciplina d = new Disciplina();
		d.setCodigo(this.codigo);
		d.setNome(this.nome);
		d.setNum_aulas(this.num_aulas);
		d.setSigla(this.sigla);
		d.setTurno(this.turno);
		return d;
	}
}
