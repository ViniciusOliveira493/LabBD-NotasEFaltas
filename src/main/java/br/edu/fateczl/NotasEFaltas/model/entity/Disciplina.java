package br.edu.fateczl.NotasEFaltas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.fateczl.NotasEFaltas.model.dto.DisciplinaDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.interfaces.IEntity;

@Entity
@Table(name = "tbDisciplina")
public class Disciplina implements IEntity<DisciplinaDTO>{
	@Id
	@Column(name = "codigo",nullable = false)
	private Long codigo;
	@Column(name = "nome",length = 60,nullable = false)
	private String nome;
	@Column(name = "sigla",length = 6,nullable = false)
	private String sigla;
	@Column(name = "turno",length = 11,nullable = false)
	private String turno;
	@Column(name = "numAulas",nullable = false)
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
	public DisciplinaDTO toDTO() {
		DisciplinaDTO d = new DisciplinaDTO();
		d.setCodigo(this.codigo);
		d.setNome(this.nome);
		d.setNum_aulas(this.num_aulas);
		d.setSigla(this.sigla);
		d.setTurno(this.turno);
		return d;
	}
}
