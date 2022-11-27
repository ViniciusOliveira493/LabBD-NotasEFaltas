package br.edu.fateczl.NotasEFaltas.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.NotasEFaltas.model.entity.Aluno;
import br.edu.fateczl.NotasEFaltas.model.entity.Falta;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	@Query(
			value = "SELECT "
					+ " a.ra"
					+ " ,a.nome "
				+ " FROM tbAluno AS a,tbFaltas AS f"
				+ " WHERE a.ra = f.raAluno AND "
				+ " f.codigoDisciplina = ?1",
			nativeQuery = true
	)
	List<Aluno> findAllDisciplina(BigInteger a);
}
