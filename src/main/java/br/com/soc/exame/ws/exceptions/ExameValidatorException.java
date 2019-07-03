package br.com.soc.exame.ws.exceptions;

import java.util.List;

import javax.xml.ws.WebFault;

@WebFault(name = "ExameFault")
public class ExameValidatorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	public ExameValidatorException(List<String> erros) {
		super(erros.toString());
	}
	
	
	public String getFaultInfo() {
		return "Dados inválidos";
	}
	
}
