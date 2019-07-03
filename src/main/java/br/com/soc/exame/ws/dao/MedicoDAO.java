package br.com.soc.exame.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.exame.ws.jdbc.ConnectionFactory;
import br.com.soc.exame.ws.model.Medico;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MedicoDAO {

	private Connection connection;

	
	public Medico buscarPorId(Long idMedico) {
		Medico medico = null;

		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM medicos p ");
			query.append("WHERE p.idMedico = ? ");

			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());
			prepareStatement.setLong(1, idMedico);

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				medico = new Medico(rs.getLong("idMedico"), rs.getString("nomeMedico"), rs.getString("crmMedico"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}

		return medico;
	}


	public List<Medico> getTodosMedicos() {
		List<Medico> medicos = new ArrayList<>();

		try {
			connection = ConnectionFactory.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM medicos ");
			query.append("ORDER BY nomeMedico");

			PreparedStatement prepareStatement = connection.prepareStatement(query.toString());

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {
				Medico medico = new Medico(rs.getLong("idMedico"), rs.getString("nomeMedico"), rs.getString("crmMedico"));
				medicos.add(medico);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}

		return medicos;
	}

}
