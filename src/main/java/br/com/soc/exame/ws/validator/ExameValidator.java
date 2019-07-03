package br.com.soc.exame.ws.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.exame.ws.exceptions.ExameValidatorException;
import br.com.soc.exame.ws.model.Exame;
import br.com.soc.exame.ws.model.Medico;
import br.com.soc.exame.ws.model.Paciente;

public class ExameValidator {

	private Exame exame;

	public ExameValidator(Exame exame) {
		this.exame = exame;
	}

	public void validate() throws ExameValidatorException {
		List<String> erros = new ArrayList<>();

		LocalDate data = exame.getData();
		if (data == null) {
			erros.add("O campo 'Data' deve ser informado com um valor válido e no formado 'dd/MM/yyyy'");
		}

		LocalTime hora = exame.getHora();
		if (hora == null) {
			erros.add("O campo 'Hora' deve ser informado com um valor válido e no formado 'HH:MM'.");
		}

		String diagnostico = exame.getDiagnostico();
		if(diagnostico != null && !diagnostico.trim().equals("")) {
			if (diagnostico.length() < 3 || diagnostico.length() > 250) {
				erros.add("O texto do diagnóstico deve conter no mínimo 3 e no máximo 250 caracteres");
			}
		}

		Medico medico = exame.getMedico();
		if (medico == null) {
			erros.add("O campo 'Médico' deve ser informado com um valor válido.");
		}
		
		Paciente paciente = exame.getPaciente();
		if (paciente == null) {
			erros.add("O campo 'Paciente' deve ser informado com um valor válido.");
		}

		if (!erros.isEmpty()) {
			throw new ExameValidatorException(erros);
		}
	}

}
