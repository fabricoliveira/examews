package br.com.soc.exame.ws.service;

import java.util.List;

import br.com.soc.exame.ws.dao.ExameDAO;
import br.com.soc.exame.ws.dto.AdicionaExameDTO;
import br.com.soc.exame.ws.dto.AtualizaExameDTO;
import br.com.soc.exame.ws.dto.ListaExamesDTO;
import br.com.soc.exame.ws.exceptions.AutorizacaoException;
import br.com.soc.exame.ws.exceptions.ExameValidatorException;
import br.com.soc.exame.ws.exceptions.ParametroInvalidoException;
import br.com.soc.exame.ws.model.Exame;
import br.com.soc.exame.ws.model.Medico;
import br.com.soc.exame.ws.model.Paciente;
import br.com.soc.exame.ws.model.Token;
import br.com.soc.exame.ws.validator.AdicionaExameDTOValidator;
import br.com.soc.exame.ws.validator.AtualizaExameDTOValidator;

public class ExameService {

	private ExameDAO exameDAO;
	
	private PacienteService pacienteService;
	private MedicoService medicoService;
	private TokenService tokenService;

	
	public ExameService() {
		exameDAO = new ExameDAO();
	}

	
	public Exame adicionarExame(Token token, AdicionaExameDTO exame) throws AutorizacaoException, ExameValidatorException {	
		
		tokenService = new TokenService();
		
		boolean ehValido = tokenService.ehValido(token);
		
		if(!ehValido) {
			throw new AutorizacaoException("Falha de autenticação");
		}
		
		new AdicionaExameDTOValidator(exame).validate();
		
		pacienteService = new PacienteService();
		medicoService = new MedicoService();
		tokenService = new TokenService();
		
		Paciente paciente = pacienteService.getPacientePorId(exame.getIdPaciente());
		Medico medico = medicoService.getMedicoPorId(exame.getIdMedico());
		
		Exame _exame = new Exame(null, exame.getData(), exame.getHora(), exame.getDiagnostico(), paciente, medico);
		return exameDAO.salvarExame(_exame);
	}

	
	public Exame getExamePorId(Long idExame) throws ParametroInvalidoException {
		
		if(idExame == null) {
			throw new ParametroInvalidoException("Falha na entrada de dados");
		}
		
		return exameDAO.getExamePorId(idExame);
	}
	

	public ListaExamesDTO getTodosExames() {
		List<Exame> exames = exameDAO.getTodosExames();
		ListaExamesDTO listaExames = new ListaExamesDTO(exames);
		return listaExames;
	}
	

	public ListaExamesDTO getTodosExamesPorPaciente(Long idPaciente) throws ParametroInvalidoException {
		
		if(idPaciente == null) {
			throw new ParametroInvalidoException("Falha na entrada de dados");
		}
		
		List<Exame> exames = exameDAO.getTodosExamesPorPaciente(idPaciente);
		ListaExamesDTO listaExames = new ListaExamesDTO(exames);
		return listaExames;
	}

	
	public ListaExamesDTO getTodosExamesPorMedico(Long idMedico) throws ParametroInvalidoException {
		
		if(idMedico == null) {
			throw new ParametroInvalidoException("Falha na entrada de dados");
		}
		
		List<Exame> exames = exameDAO.getTodosExamesPorMedico(idMedico);
		ListaExamesDTO listaExames = new ListaExamesDTO(exames);
		return listaExames;
	}
	
	
	public ListaExamesDTO getTodosExamesPorMedicoEPaciente(Long idMedico, Long idPaciente) throws ParametroInvalidoException {
		
		if(idMedico == null || idPaciente == null) {
			throw new ParametroInvalidoException("Falha na entrada de dados");
		}
		
		List<Exame> exames = exameDAO.getTodosExamesPorMedicoEPaciente(idMedico, idPaciente);
		ListaExamesDTO listaExames = new ListaExamesDTO(exames);
		return listaExames;
	}

	
	public Exame atualizarExame(Token token, AtualizaExameDTO exame) throws AutorizacaoException, ExameValidatorException {
		
		tokenService = new TokenService();
		
		boolean ehValido = tokenService.ehValido(token);
		
		if(!ehValido) {
			throw new AutorizacaoException("Falha de autenticação");
		}
		
		new AtualizaExameDTOValidator(exame).validate();
		
		pacienteService = new PacienteService();
		medicoService = new MedicoService();
		tokenService = new TokenService();
		
		Paciente paciente = pacienteService.getPacientePorId(exame.getIdPaciente());
		Medico medico = medicoService.getMedicoPorId(exame.getIdMedico());
		
		Exame _exame = new Exame(exame.getId(), exame.getData(), exame.getHora(), exame.getDiagnostico(), paciente, medico);
		
		return exameDAO.salvarExame(_exame);
	}

	
	public boolean deletarExame(Token token, Long idExame) throws AutorizacaoException, ParametroInvalidoException {
		tokenService = new TokenService();
		
		boolean ehValido = tokenService.ehValido(token);
		
		if(!ehValido) {
			throw new AutorizacaoException("Falha de autenticação");
		}
		
		if(idExame == null) {
			throw new ParametroInvalidoException("Falha na entrada de dados");
		}
		
		return exameDAO.deletarExame(idExame) == 1 ? true : false;
	}

}
