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
import br.edu.fateczl.NotasEFaltas.model.dto.DisciplinaDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Disciplina;
import br.edu.fateczl.NotasEFaltas.repository.DisciplinaRepository;

@RestController
@RequestMapping("/api")
public class DisciplinaController extends Controller<DisciplinaDTO>{
	@Autowired
	DisciplinaRepository rep;
	
	@Override
	@GetMapping("/disciplina")
	public List<DisciplinaDTO> findAll() {
		List<Disciplina> d = rep.findAll();
		List<DisciplinaDTO> list = new ArrayList<>();
		for(Disciplina dis:d) {
			list.add(dis.toDTO());
		}
		return list;
	}

	@Override
	@GetMapping("/disciplina/{id}")
	public ResponseEntity<DisciplinaDTO> findOne(@PathVariable(name = "id") Long a) {
		Optional<Disciplina> opt = rep.findById(a);
		Disciplina d = opt.orElseThrow(()-> new ResourceNotFoundException(this.notFound("uma disciplina", a+"")));
		return ResponseEntity.ok().body(d.toDTO());
	}

	@Override
	@PostMapping("/disciplina")
	public ResponseEntity<String> insert(@Valid @RequestBody DisciplinaDTO obj) {
		rep.save(obj.toEntity());
		return respostaOK(1);
	}

	@Override
	@PutMapping("/disciplina")
	public ResponseEntity<String> update(@Valid @RequestBody DisciplinaDTO obj) {
		rep.save(obj.toEntity());
		return respostaOK(2);
	}

	@Override
	@DeleteMapping("/disciplina")
	public ResponseEntity<String> delete(@Valid @RequestBody DisciplinaDTO obj) {
		rep.delete(obj.toEntity());
		return respostaOK(3);
	}

}
