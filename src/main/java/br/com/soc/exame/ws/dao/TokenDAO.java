package br.com.soc.exame.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.soc.exame.ws.jdbc.ConnectionFactory;
import br.com.soc.exame.ws.model.Token;

public class TokenDAO {
	
	private Connection connection;
	
	public boolean ehValido(Token token) {
		boolean isValid = false;
		
		try {
			connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tokens WHERE token = ?");
			preparedStatement.setString(1, token.getToken());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				LocalDate hoje = LocalDate.now();
				LocalDate validade = rs.getDate("validade").toLocalDate();
				
				if(validade.isAfter(hoje) || validade.isEqual(hoje))
					isValid = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection);
		}
		
		return isValid;
	}

}
