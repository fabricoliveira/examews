package br.com.soc.exame.ws.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.soc.exame.ws.adapters.LocalDateAdapter;
import br.com.soc.exame.ws.adapters.LocalTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * 
 * Classe criada com o objetivo de estilizar o body do SOAP
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AtualizaExameDTO {
	
	private Long id;
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate data;
	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	private LocalTime hora;
	private String diagnostico;
	private Long idPaciente;
	private Long idMedico;

}
