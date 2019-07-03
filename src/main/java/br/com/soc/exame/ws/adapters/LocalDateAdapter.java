package br.com.soc.exame.ws.adapters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * 
 * Classe criada com o objetivo de estilizar o body e a resposta do SOAP, convertendo a exibição dos dados do tipo LocalDate
 * 
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String data) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatter);
	}

	@Override
	public String marshal(LocalDate data) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return data.format(formatter);
	}

}
