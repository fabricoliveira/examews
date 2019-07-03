package br.com.soc.exame.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
	
	private Long id;
	private String nome;
	private Integer idade;
	
	
	public Paciente(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

}
