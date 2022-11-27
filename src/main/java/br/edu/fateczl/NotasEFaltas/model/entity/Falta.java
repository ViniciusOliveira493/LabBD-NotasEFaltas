package br.edu.fateczl.NotasEFaltas.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.fateczl.NotasEFaltas.model.dto.FaltaDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.interfaces.IEntity;

@Entity
@Table(name = "tbFaltas")
public class Falta implements IEntity<FaltaDTO>{
	@EmbeddedId
	private FaltaID id;
	@Column(name = "presenca",nullable = false)
	private Integer presenca;
	
	public FaltaID getId() {
		return id;
	}
	public void setId(FaltaID id) {
		this.id = id;
	}
	public int getPresenca() {
		return presenca;
	}
	public void setPresenca(int presenca) {
		this.presenca = presenca;
	}
	@Override
	public FaltaDTO toDTO() {
		FaltaDTO f = new FaltaDTO();
		f.setCodigo(this.id.getCodigo());
		f.setData(this.id.getData());
		f.setPresenca(this.presenca);
		f.setRA(this.id.getRA());
		return f;
	}
	
	public static LocalDate parseDate(String date) {
		DateTimeFormatter frm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date,frm);
	}
}
