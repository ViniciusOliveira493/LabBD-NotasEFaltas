package br.edu.fateczl.NotasEFaltas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.NotasEFaltas.model.entity.Falta;
import br.edu.fateczl.NotasEFaltas.model.entity.FaltaID;

public interface FaltaRepository extends JpaRepository<Falta, FaltaID>{
	@Modifying
	@Query(value = "SELECT "
					+ " raAluno "
					+ " ,codigoDisciplina "
					+ " ,data "
					+ " ,presenca "
					+ " FROM tbFaltas "
					+ " WHERE raAluno = ?1 AND codigoDisciplina = ?2",
			nativeQuery = true)
	List<Falta> faltas(Long aluno,Long disciplina);
}
