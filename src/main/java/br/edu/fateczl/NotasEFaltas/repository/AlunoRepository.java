package br.edu.fateczl.NotasEFaltas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateczl.NotasEFaltas.model.entity.Aluno;
import br.edu.fateczl.NotasEFaltas.model.entity.Falta;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
}
