package br.edu.fateczl.NotasEFaltas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateczl.NotasEFaltas.model.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
