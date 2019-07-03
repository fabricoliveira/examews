package br.com.soc.exame.ws.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.soc.exame.ws.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 * Classe criada com o objetivo de estilizar a resposta do SOAP
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaMedicosDTO {
	
	@XmlElement(name = "medico")
	private List<Medico> medicos;

}
