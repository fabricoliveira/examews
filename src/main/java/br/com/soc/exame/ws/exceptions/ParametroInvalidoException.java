package br.com.soc.exame.ws.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "ParametroInvalidoFault")
public class ParametroInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public ParametroInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	
	public String getFaultInfo() {
		return "Parametro inválido";
	}

}
