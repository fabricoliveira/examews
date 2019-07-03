package br.com.soc.exame.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.exame.ws.exceptions.ExameValidatorException;
import br.com.soc.exame.ws.jdbc.ConnectionFactory;
import br.com.soc.exame.ws.model.Exame;
import br.com.soc.exame.ws.model.Medico;
import br.com.soc.exame.ws.model.Paciente;
import br.com.soc.exame.ws.service.MedicoService;
import br.com.soc.exame.ws.service.PacienteService;
import br.com.soc.exame.ws.validator.ExameValidator;

public class ExameDAO {
	
	private Connection connection;
	
	private PacienteService pacienteService;
	private MedicoService medicoService;
	
	
	public Exame salvarExame(Exame exame) throws ExameValidatorException {
		
		new ExameValidator(exame).validate();
		
		PreparedStatement prepareStatement;
		int rowsAffected = 0;
		boolean isUpdate = (exame.getId() != null) ? true : false;
		
		
		try {
			connection = ConnectionFactory.getConnection();
			
			if(isUpdate) {
				prepareStatement = connection.prepareStatement("UPDATE exames SET dataExame=?, horaExame=?, diagnosticoExame=?, idPaciente=?, idMedico=? WHERE idExame=?", Statement.RETURN_GENERATED_KEYS);
				prepareStatement.setLong(6, exame.getId());
			} else {
				prepareStatement = connection.prepareStatement("INSERT INTO exames (dataExame, horaExame, diagnosticoExame, idPaciente, idMedico) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			}
			
			prepareStatement.setDate(1, java.sql.Date.valueOf(exame.getData()));
			prepareStatement.setTime(2, java.sql.Time.valueOf(exame.getHora()));
			prepareStatement.setString(3, exame.getDiagnostico());
			prepareStatement.setLong(4, exame.getPaciente().getId());
			prepareStatement.setLong(5, exame.getMedico().getId());
			
			rowsAffected = prepareStatement.executeUpdate();
			
			if (rowsAffected == 0) {
	            throw new SQLException("Erro ao salvar o exame!!!");
	        }
			
			try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
	            if (!isUpdate) {
	            	if(generatedKeys.next()) {
		                exame.setId(generatedKeys.getLong(1));
		            }
		            else {
		                throw new SQLException("Erro ao salvar o exame!!!");
		            }
	            }
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exame;
	}
	
	
	public Exame getExamePorId(Long idExame) {
		Exame exame = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.* ");
			query.append("FROM exames e ");
			query.append("INNER JOIN pacientes p ");
			query.append("ON e.idPaciente = p.idPaciente ");
			query.append("INNER JOIN medicos m ");
			query.append("ON e.idMedico = m.idMedico ");
			query.append("WHERE e.idExame = ? ");
		
			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idExame);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				exame = recuperaExame(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exame;
	}
	
	
	public List<Exame> getTodosExames() {
		List<Exame> exames = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.* ");
			query.append("FROM exames e ");
			query.append("INNER JOIN pacientes p ");
			query.append("ON e.idPaciente = p.idPaciente ");
			query.append("INNER JOIN medicos m ");
			query.append("ON e.idMedico = m.idMedico ");
			query.append("ORDER BY nomePaciente, dataExame, horaExame");
		
			ResultSet rs = connection.prepareStatement(query.toString()).executeQuery();
			
			while(rs.next()) {
				Exame exame = recuperaExame(rs);
				exames.add(exame);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exames;
	}
	
	
	public List<Exame> getTodosExamesPorPaciente(Long idPaciente) {
		List<Exame> exames = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.* ");
			query.append("FROM exames e ");
			query.append("INNER JOIN pacientes p ");
			query.append("ON e.idPaciente = p.idPaciente ");
			query.append("INNER JOIN medicos m ");
			query.append("ON e.idMedico = m.idMedico ");
			query.append("WHERE p.idPaciente = ? ");
			query.append("ORDER BY dataExame, horaExame");
		
			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idPaciente);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				Exame exame = recuperaExame(rs);
				exames.add(exame);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exames;
	}
	
	
	public List<Exame> getTodosExamesPorMedico(Long idMedico) {
		List<Exame> exames = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.* ");
			query.append("FROM exames e ");
			query.append("INNER JOIN pacientes p ");
			query.append("ON e.idPaciente = p.idPaciente ");
			query.append("INNER JOIN medicos m ");
			query.append("ON e.idMedico = m.idMedico ");
			query.append("WHERE m.idMedico = ? ");
			query.append("ORDER BY dataExame, horaExame");
		
			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idMedico);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				Exame exame = recuperaExame(rs);
				exames.add(exame);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exames;
	}
	
	
	public List<Exame> getTodosExamesPorMedicoEPaciente(Long idMedico, Long idPaciente) {
		List<Exame> exames = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.* ");
			query.append("FROM exames e ");
			query.append("INNER JOIN pacientes p ");
			query.append("ON e.idPaciente = p.idPaciente ");
			query.append("INNER JOIN medicos m ");
			query.append("ON e.idMedico = m.idMedico ");
			query.append("WHERE m.idMedico = ? ");
			query.append("AND p.idPaciente = ? ");
			query.append("ORDER BY dataExame, horaExame");
		
			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idMedico);
			prepareStatement.setLong(2, idPaciente);
			
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				Exame exame = recuperaExame(rs);
				exames.add(exame);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return exames;
	}
	
	
	public int deletarExame(Long idExame) {
		int rowsAffected = 0;
		
		try {
			connection = ConnectionFactory.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM exames WHERE idExame = ?");
			prepareStatement.setLong(1, idExame);
			rowsAffected = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return rowsAffected;
	}
	
	private Exame recuperaExame(ResultSet rs) throws SQLException {
		pacienteService = new PacienteService();
		medicoService = new MedicoService();
		
		Paciente paciente = pacienteService.getPacientePorId(rs.getLong("idPaciente"));
		Medico medico = medicoService.getMedicoPorId(rs.getLong("idMedico"));
		return new Exame(rs.getLong("idExame"), rs.getDate("dataExame").toLocalDate(), rs.getTime("horaExame").toLocalTime(), rs.getString("diagnosticoExame"), paciente, medico);
	}

}
