package br.edu.fateczl.NotasEFaltas.controller;

import java.time.LocalDate;
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

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.edu.fateczl.NotasEFaltas.controller.interfaces.Controller;
import br.edu.fateczl.NotasEFaltas.model.dto.FaltaDTO;
import br.edu.fateczl.NotasEFaltas.model.entity.Falta;
import br.edu.fateczl.NotasEFaltas.model.entity.FaltaID;
import br.edu.fateczl.NotasEFaltas.repository.FaltaRepository;

@RestController
@RequestMapping("/api")
public class FaltaController extends Controller<FaltaDTO>{
	@Autowired
	FaltaRepository rep;
	
	@Override
	@GetMapping("/falta")
	public List<FaltaDTO> findAll() {
		List<Falta> faltas = rep.findAll();
		List<FaltaDTO> list = new ArrayList<>();
		for(Falta f:faltas) {
			list.add(f.toDTO());
		}
		return list;
	}

	@GetMapping("/falta/{ra}/{disciplina}/{data}")
	public ResponseEntity<FaltaDTO> findOne(@PathVariable(name = "ra") Long ra
											,@PathVariable(name = "disciplina") Long disciplina
											,@PathVariable(name = "data") String data) {
		FaltaID id = new FaltaID();
		id.setRA(ra);
		id.setCodigo(disciplina);
		id.setData(Falta.parseDate(data));
		Optional<Falta> falta = rep.findById(id);
		Falta f = falta.orElseThrow(()-> new ResourceNotFoundException
		  (this.notFound("uma falta", ra+" com disciplina = "+disciplina+" na data = "+data.toString())));
		return ResponseEntity.ok().body(f.toDTO());
	}

	@Override
	@PostMapping("/falta")
	public ResponseEntity<String> insert(@Valid @RequestBody FaltaDTO obj) {
		rep.save(obj.toEntity());
		return respostaOK(1);
	}

	@Override
	@PutMapping("/falta")
	public ResponseEntity<String> update(@Valid @RequestBody FaltaDTO obj) {
		rep.save(obj.toEntity());
		return respostaOK(2);
	}

	@Override
	@DeleteMapping("/falta")
	public ResponseEntity<String> delete(@Valid @RequestBody FaltaDTO obj) {
		rep.delete(obj.toEntity());
		return respostaOK(3);
	}

	@Override
	public ResponseEntity<FaltaDTO> findOne(Long a) {
		// TODO Auto-generated method stub
		return null;
	}

}
