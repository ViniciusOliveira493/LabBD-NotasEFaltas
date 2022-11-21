package br.edu.fateczl.NotasEFaltas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateczl.NotasEFaltas.model.entity.Falta;
import br.edu.fateczl.NotasEFaltas.model.entity.FaltaID;

public interface FaltaRepository extends JpaRepository<Falta, FaltaID>{

}
