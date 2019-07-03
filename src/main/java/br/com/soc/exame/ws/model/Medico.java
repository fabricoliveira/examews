package br.com.soc.exame.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
	
	private Long id;
	private String nome;
	private String crm;
	
	public Medico(String nome, String crm) {
		this.nome = nome;
		this.crm = crm;
	}

}
