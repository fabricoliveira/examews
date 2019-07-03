package main;
import javax.xml.ws.Endpoint;
import br.com.soc.exame.ws.ExameWS;

public class PublicaWS {
	
	public static void main(String[] args) {
		
		ExameWS exameWS = new ExameWS();
		String url = "http://localhost:9090/ExameWS?wsdl";
		
		System.out.println("ExameWS running ---> " + url);
		
		Endpoint.publish(url, exameWS);
		
	}

}
