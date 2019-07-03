package br.com.soc.exame.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.exame.ws.jdbc.ConnectionFactory;
import br.com.soc.exame.ws.model.Paciente;

public class PacienteDAO {

	private Connection connection;

	
	public Paciente buscarPorId(Long idPaciente) {
		Paciente paciente = null;

		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM pacientes p ");
			query.append("WHERE p.idPaciente = ? ");

			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idPaciente);

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				paciente = new Paciente(rs.getLong("idPaciente"), rs.getString("nomePaciente"), rs.getInt("idadePaciente"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}

		return paciente;
	}


	public List<Paciente> getTodosPacientes() {
		List<Paciente> pacientes = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM pacientes ");
			query.append("ORDER BY nomePaciente");

			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				Paciente paciente = new Paciente(rs.getLong("idPaciente"), rs.getString("nomePaciente"), rs.getInt("idadePaciente"));
				pacientes.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}

		return pacientes;
	}

}
