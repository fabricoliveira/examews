package br.com.soc.exame.ws.service;

import java.util.List;

import br.com.soc.exame.ws.dao.MedicoDAO;
import br.com.soc.exame.ws.dto.ListaMedicosDTO;
import br.com.soc.exame.ws.model.Medico;

public class MedicoService {
	
	private MedicoDAO medicoDAO;
	
	
	public MedicoService() {
		medicoDAO = new MedicoDAO();
	}
	
	
	public Medico getMedicoPorId(Long idMedico) {
		return medicoDAO.buscarPorId(idMedico);
	}
	
	
	public ListaMedicosDTO getTodosMedicos() {
		List<Medico> medicos = medicoDAO.getTodosMedicos();
		ListaMedicosDTO listaMedicos = new ListaMedicosDTO(medicos);
		return listaMedicos;
	}
	
}
