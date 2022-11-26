package br.edu.fateczl.NotasEFaltas.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.dao.RelatorioFaltasDAO;
import br.edu.fateczl.NotasEFaltas.repository.relatorioFaltas.model.RelatorioFalta;

@RestController
@RequestMapping("/api")
public class RelatorioFaltasController {	
	RelatorioFaltasDAO rep = new RelatorioFaltasDAO();
	
	@GetMapping("/relatoriofaltas/{disc}")
	public List<RelatorioFalta> findAll(@PathVariable(name = "disc") BigInteger id) {
		List<RelatorioFalta> list = rep.obterRelatorioDisciplina(id);
		return list;
	}
}
