package br.com.soc.exame.ws.service;

import java.util.List;

import br.com.soc.exame.ws.dao.PacienteDAO;
import br.com.soc.exame.ws.dto.ListaPacientesDTO;
import br.com.soc.exame.ws.model.Paciente;

public class PacienteService {
	
	private PacienteDAO pacienteDAO;
	
	
	public PacienteService() {
		pacienteDAO = new PacienteDAO();
	}
	
	
	public Paciente getPacientePorId(Long idPaciente) {
		return pacienteDAO.buscarPorId(idPaciente);
	}


	public ListaPacientesDTO getTodosPacientes() {
		List<Paciente> pacientes = pacienteDAO.getTodosPacientes();
		ListaPacientesDTO listaPacientes = new ListaPacientesDTO(pacientes);
		return listaPacientes;
	}
	
}
