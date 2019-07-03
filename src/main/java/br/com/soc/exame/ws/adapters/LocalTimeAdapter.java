package br.com.soc.exame.ws.adapters;

import java.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * 
 * Classe criada com o objetivo de estilizar o body e a resposta do SOAP, convertendo a exibição dos dados do tipo LocalTime
 * 
 */
public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

	@Override
	public LocalTime unmarshal(String hora) throws Exception {
		return LocalTime.parse(hora);
	}

	@Override
	public String marshal(LocalTime hora) throws Exception {
		return hora.toString();
	}

}
