package br.edu.fateczl.NotasEFaltas.controller.interfaces;

import org.springframework.http.ResponseEntity;

import br.edu.fateczl.NotasEFaltas.model.dto.AlunoDTO;

public abstract class Controller<T> implements IController<T>{
	protected String notFound(String nome,String id) {
		return "Não existe "+nome+" com id = "+id;
	}
	
	protected String sucesso(int acao) {
		switch (acao) {
		case 1:
			return "Dados cadastrados com sucesso";
		case 2:
			return "Dados atualizados com sucesso";
		case 3:
			return "Dados apagados com sucesso";
		}
		return "Erro ao buscar mensagem de sucesso";
	}
	
	protected ResponseEntity<String> respostaOK(int acao){
		return ResponseEntity.ok().body(this.sucesso(acao));
	}
}
