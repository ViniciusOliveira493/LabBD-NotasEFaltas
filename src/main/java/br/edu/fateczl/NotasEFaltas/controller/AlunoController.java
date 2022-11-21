package br.edu.fateczl.NotasEFaltas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateczl.NotasEFaltas.controller.interfaces.Controller;
import br.edu.fateczl.NotasEFaltas.model.dto.AlunoDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Aluno;
import br.edu.fateczl.NotasEFaltas.repository.AlunoRepository;

@RestController
@RequestMapping("/api")
public class AlunoController extends Controller<AlunoDTO> {
	@Autowired
	AlunoRepository rep;
	
	@Override
	@GetMapping("/aluno")
	public List<AlunoDTO> findAll() {
		List<Aluno> alunos = rep.findAll();
		List<AlunoDTO> list = new ArrayList<>();
		for(Aluno a:alunos) {
			list.add(a.toDTO());
		}
		return list;
	}

	@Override
	@GetMapping("/aluno/{id}")
	public ResponseEntity<AlunoDTO> findOne(@PathVariable(name = "id") Long id) {
		Optional<Aluno> a = rep.findById(id);
		Aluno al = a.orElseThrow(()-> new ResourceNotFoundException(this.notFound("o aluno", id+"")));
		return ResponseEntity.ok().body(al.toDTO());
	}

	@Override
	@PostMapping("/aluno")
	public ResponseEntity<String> insert(@Valid @RequestBody AlunoDTO obj) {
		rep.save(obj.toEntity());		
		return ResponseEntity.ok().body(sucesso(1));
	}

	@Override
	@PutMapping("/aluno")
	public ResponseEntity<String> update(@Valid @RequestBody AlunoDTO obj) {
		rep.save(obj.toEntity());		
		return ResponseEntity.ok().body(sucesso(2));
	}

	@Override
	@DeleteMapping("/aluno")
	public ResponseEntity<String> delete(@Valid @RequestBody AlunoDTO obj) {
		rep.delete(obj.toEntity());		
		return ResponseEntity.ok().body(sucesso(3));
	}
}