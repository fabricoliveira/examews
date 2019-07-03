# Exame WS


Esse projeto é um Web Service SOAP, que realiza um CRUD com o banco de dados MySQL, para integração com sistema web em Java com Struts 2.

<br> 
Para gerar o arquivo ExameWS.wsdl, você pode rodar o comando abaixo:


		mvn clean install
	
O arquivo será gerado e colocado na pasta "target/generated/wsdl".


<br>


Nesse projeto, foi utilizado o Lombok, que gera nas classes os métodos getters/setters, construtores, toString, equals e hashcode, entre outros.


Para o Lombok funcionar na sua IDE, é necessário instalar o Jar do Lombok na sua IDE. Para mais detalhes, visite a página abaixo:

		
		https://projectlombok.org/