package br.com.soc.exame.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.soc.exame.ws.dto.AdicionaExameDTO;
import br.com.soc.exame.ws.dto.AtualizaExameDTO;
import br.com.soc.exame.ws.dto.ListaExamesDTO;
import br.com.soc.exame.ws.dto.ListaMedicosDTO;
import br.com.soc.exame.ws.dto.ListaPacientesDTO;
import br.com.soc.exame.ws.exceptions.AutorizacaoException;
import br.com.soc.exame.ws.exceptions.ExameValidatorException;
import br.com.soc.exame.ws.exceptions.ParametroInvalidoException;
import br.com.soc.exame.ws.model.Exame;
import br.com.soc.exame.ws.model.Medico;
import br.com.soc.exame.ws.model.Paciente;
import br.com.soc.exame.ws.model.Token;
import br.com.soc.exame.ws.service.ExameService;
import br.com.soc.exame.ws.service.MedicoService;
import br.com.soc.exame.ws.service.PacienteService;

@WebService(portName = "ExameWS", serviceName = "ExameWSService", name = "ExameWS", targetNamespace = "http://ws.exame.soc.com.br/")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class ExameWS {

	private ExameService exameService = new ExameService();
	private MedicoService medicoService = new MedicoService();
	private PacienteService pacienteService = new PacienteService();

	@WebMethod(action = "AdicionarUmExame", operationName = "AdicionarUmExame")
	@WebResult(name = "exame")
	public Exame adicionarExame(@WebParam(name = "token", header = true) Token token, @WebParam(name = "exame") AdicionaExameDTO exame) throws AutorizacaoException, ExameValidatorException {
		return exameService.adicionarExame(token, exame);
	}

	
	@WebMethod(action = "BuscarExamePorId", operationName = "BuscarExamePorId")
	@WebResult(name = "exame")
	public Exame getExamePorId(@WebParam(name = "idExame") Long idExame) throws ParametroInvalidoException {
		return exameService.getExamePorId(idExame);
	}

	
	@WebMethod(action = "BuscarTodosOsExames", operationName = "BuscarTodosOsExames")
	@WebResult(name = "exames")
	public ListaExamesDTO getTodosExames() {
		return exameService.getTodosExames();
	}

	
	@WebMethod(action = "BuscarTodosOsExamesPorPaciente", operationName = "BuscarTodosOsExamesPorPaciente")
	@WebResult(name = "exames")
	public ListaExamesDTO getTodosExamesPorPaciente(@WebParam(name = "idPaciente") Long idPaciente) throws ParametroInvalidoException {
		return exameService.getTodosExamesPorPaciente(idPaciente);
	}

	
	@WebMethod(action = "BuscarTodosOsExamesPorMedico", operationName = "BuscarTodosOsExamesPorMedico")
	@WebResult(name = "exames")
	public ListaExamesDTO getTodosExamesPorMedico(@WebParam(name = "idMedico") Long idMedico) throws ParametroInvalidoException {
		return exameService.getTodosExamesPorMedico(idMedico);
	}
	
	
	@WebMethod(action = "BuscarTodosOsExamesPorMedicoEPaciente", operationName = "BuscarTodosOsExamesPorMedicoEPaciente")
	@WebResult(name = "exames")
	public ListaExamesDTO getTodosExamesPorMedicoEPaciente(@WebParam(name = "idMedico") Long idMedico, @WebParam(name = "idPaciente") Long idPaciente) throws ParametroInvalidoException {
		return exameService.getTodosExamesPorMedicoEPaciente(idMedico, idPaciente);
	}

	
	@WebMethod(action = "AtualizarUmExame", operationName = "AtualizarUmExame")
	@WebResult(name = "exame")
	public Exame atualizarExame(@WebParam(name = "token", header = true) Token token, @WebParam(name = "exame") AtualizaExameDTO exame) throws AutorizacaoException, ExameValidatorException {
		return exameService.atualizarExame(token, exame);
	}

	
	@WebMethod(action = "DeletarUmExame", operationName = "DeletarUmExame")
	@WebResult(name = "exame")
	public boolean deletarExame(@WebParam(name = "token", header = true) Token token, @WebParam(name = "idExame") Long idExame) throws AutorizacaoException, ParametroInvalidoException {
		return exameService.deletarExame(token, idExame);
	}
	
	
	@WebMethod(action = "BuscarMedicoPorId", operationName = "BuscarMedicoPorId")
	@WebResult(name = "medico")
	public Medico getMedicoPorId(@WebParam(name = "idMedico") Long idMedico) {
		return medicoService.getMedicoPorId(idMedico);
	}
	
	
	@WebMethod(action = "BuscarTodosOsMedicos", operationName = "BuscarTodosOsMedicos")
	@WebResult(name = "medicos")
	public ListaMedicosDTO getTodosMedicos() {
		return medicoService.getTodosMedicos();
	}
	
	
	@WebMethod(action = "BuscarPacientePorId", operationName = "BuscarPacientePorId")
	@WebResult(name = "paciente")
	public Paciente getPacientePorId(@WebParam(name = "idPaciente") Long idPaciente) {
		return pacienteService.getPacientePorId(idPaciente);
	}
	
	
	@WebMethod(action = "BuscarTodosOsPacientes", operationName = "BuscarTodosOsPacientes")
	@WebResult(name = "pacientes")
	public ListaPacientesDTO getTodosPacientes() {
		return pacienteService.getTodosPacientes();
	}

}
