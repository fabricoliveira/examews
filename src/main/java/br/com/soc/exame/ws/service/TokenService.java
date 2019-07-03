package br.com.soc.exame.ws.service;

import br.com.soc.exame.ws.dao.TokenDAO;
import br.com.soc.exame.ws.model.Token;

public class TokenService {
	
	private TokenDAO tokenDAO;
	
	
	public TokenService() {
		tokenDAO = new TokenDAO();
	}
	
	
	public boolean ehValido(Token token) {
		return tokenDAO.ehValido(token);
	}

}
