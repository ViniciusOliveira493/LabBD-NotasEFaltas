package br.edu.fateczl.NotasEFaltas.model.dto;

import java.time.LocalDate;

import br.edu.fateczl.NotasEFaltas.model.dto.interfaces.IDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Falta;
import br.edu.fateczl.NotasEFaltas.model.entity.FaltaID;

public class FaltaDTO implements IDTO<Falta>{
	private Long RA;
	private Long codigo;
	private LocalDate data;
	private Integer presenca;
	
	public Long getRA() {
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
	public int getPresenca() {
		return presenca;
	}
	public void setPresenca(int presenca) {
		this.presenca = presenca;
	}
	@Override
	public Falta toEntity() {
		FaltaID id = new FaltaID();
		id.setCodigo(this.codigo);
		id.setData(this.data);
		id.setRA(this.RA);
		Falta f = new Falta();
		f.setId(id);
		f.setPresenca(this.presenca);
		return f;
	}
	
	
}
