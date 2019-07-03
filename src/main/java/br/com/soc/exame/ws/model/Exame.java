package br.com.soc.exame.ws.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.soc.exame.ws.adapters.LocalDateAdapter;
import br.com.soc.exame.ws.adapters.LocalTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Exame {
	
	private Long id;
	
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(required=true)
	private LocalDate data;

	@XmlJavaTypeAdapter(value = LocalTimeAdapter.class)
	@XmlElement(required=true)
	private LocalTime hora;
	
	private String diagnostico;
	
	@XmlElement(required=true)
	private Paciente paciente;
	
	@XmlElement(required=true)
	private Medico medico;

}
