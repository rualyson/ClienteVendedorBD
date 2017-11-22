package projeto.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BancoDeDados {
	
	private Connection connection = null; //gerencia a conexão com o banco de dados
	private Statement statement = null;	//gerencia as consultas
	private ResultSet resultset = null;	//retorna um conjunto de informações do SELECT
	
//conecta o banco de dados
	public void conectar () {
		String servidor = "localhost";
		String user = "root";
		String senha = "99196160";
		String port = "3306";
        String database = "VPC";
		String driver = "com.mysql.jdbc.Driver"; //direciona o java para o mysql		
//tratamento de exceções
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://" + servidor + ":" + port + "/" + database;
			this.connection = DriverManager.getConnection(url, user, senha);
			this.statement = (Statement) this.connection.createStatement();			         
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public boolean estaConectado () {			
			if (this.connection != null) {
				return true;
			} else {
				return false;
			}
		}
	
	public void criarTabelaCliente () {
		try {
			Statement tabela = connection.createStatement();
			tabela.executeUpdate("CREATE TABLE cliente ("
					+"id INT primary key,"
                    +"nome VARCHAR(40) NOT NULL,"
                    +"CPF VARCHAR(15) NOT NULL,"
                    +"endereco VARCHAR(50) NOT NULL,"
                    +"cidade VARCHAR(20),"
                    +"cep VARCHAR(10),"
                    +"fone VARCHAR(16),"
                    +"id_vendedor INT ,"
    	            +" CONSTRAINT id_vendedor FOREIGN KEY(id_vendedor) REFERENCES vendedor(id_vendedor))");
			System.out.println("Table created!");
				
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void inserirCliente (int id, String nome, String CPF, String endereco, String cidade,
			String cep, String fone, int id_vendedor ) {
		try {
			String query = "INSERT INTO cliente (id, nome, CPF, endereco, cidade, cep, fone, id_vendedor)"
					+ "VALUES('" + id + "' , '" +nome+ "','" + CPF + "', '"+endereco+ "',"
					+ "'" + cidade + "', '"+ cep + "', '"+ fone +"', '"+ id_vendedor +"');";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}
	
	public void listarCliente () {
		try {
			String query = "SELECT * FROM cliente ORDER BY nome";	//faz a consulta
			this.resultset = this.statement.executeQuery(query);	//recebe a consulta
	//ele roda enquanto houver itens a serem exibidos
			while (this.resultset.next()) {
				System.out.println(
						"\n - ID: " + this.resultset.getString("id") + 
						"\n - CPF: " + this.resultset.getString("cpf") + 
						"\n - Nome: " + this.resultset.getString("nome") +
						"\n - Endereço: " + this.resultset.getString("endereco") +
						"\n - Cidade: " + this.resultset.getString("cidade") + 
						"\n - Cep: " + this.resultset.getString("cep") + 
						"\n - Fone: " + this.resultset.getString("fone")+
						"\n - id_vendedor: " + this.resultset.getString("id_Vendedor") );
			}			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	public void editarCliente (String cpf, String nome, String endereco, String cidade,
			String cep, String fone, int id) {
		try {
			String query = "UPDATE cliente SET CPF = '" + cpf +"', nome = '" +nome+ "', "
					+ " endereco = '" +endereco + "', cidade = '" + cidade + "',"
					+ "cep = '" + cep + "', fone = '" + fone + "'"
					+ " WHERE id = '" + id +"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}
	
	public void listarClienteId (int id) {
		try {
			String query = "SELECT * FROM cliente WHERE id = '" + id + "' ORDER BY nome";	//faz a consulta
			this.resultset = this.statement.executeQuery(query);	//recebe a consulta
				if (this.resultset.next()) {
					System.out.println("CPF: " + this.resultset.getString("cpf") + 
							"\n - Nome: " + this.resultset.getString("nome") +
							"\n - Endereço: " + this.resultset.getString("endereco") +
							"\n - Cidade: " + this.resultset.getString("cidade") + 
							"\n - Cep: " + this.resultset.getString("cep") + 
							"\n - Fone: " + this.resultset.getString("fone"));
				}
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}
	public void apagarCliente (int id) {
		try {
			String query = "DELETE FROM cliente WHERE ID = '" + id +"'";
			this.statement.executeUpdate(query);
				
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void criarTabelaVendedor () {
		try {
			Statement tabela = connection.createStatement();
			tabela.executeUpdate("CREATE TABLE vendedor ("
					+ "id_vendedor INT AUTO_INCREMENT PRIMARY KEY,"
					+ "nome VARCHAR (50) NOT NULL,"
					+ "cpf VARCHAR (15),"
					+ "fone VARCHAR (10))");
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void inserirVendedor (int id_vendedor, String nome, String cpf , String fone) {
		try {
			String query = "INSERT INTO vendedor (id_vendedor, nome, cpf, fone)"
					+ "VALUES('" + id_vendedor + "' ,'" +nome+ "','" + cpf + "','" +fone+ "');";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}
	
	public void listarVendedor () {
		try {
			String query = "SELECT * FROM vendedor ORDER BY nome";	//faz a consulta
			this.resultset = this.statement.executeQuery(query);	//recebe a consulta
		//ele roda enquanto houver itens a serem exibidos
			while (this.resultset.next()) {
				System.out.println("ID: " + this.resultset.getInt("id_vendedor") +
						" - Nome: " + this.resultset.getString("nome")+
						" - CPF: " + this.resultset.getString("cpf"));
			}
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}
	
	public void editarVendedor (String cpf, String nome, int id_vendedor) {
		try {
			String query = "UPDATE vendedor SET CPF = '" + cpf +"', nome = '" +nome+ "'"
					+ " WHERE id_vendedor = '" +id_vendedor+"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}
	
	public void listarVendedorId (int id_vendedor) {
		try {
			String query = "SELECT * FROM vendedor WHERE id_vendedor = '" + id_vendedor + "' ORDER BY nome";	//faz a consulta
			this.resultset = this.statement.executeQuery(query);	//recebe a consulta
		//ele roda enquanto houver itens a serem exibidos
			while (this.resultset.next()) {
				System.out.println("ID: " + this.resultset.getInt("id_vendedor") +
						" CPF: " + this.resultset.getString("cpf") + 
						" - Nome: " + this.resultset.getString("nome"));
			}
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}
	
	public void apagarVendedor (int id_vendedor) {
		try {
			String query = "DELETE FROM vendedor WHERE id_vendedor = '" + id_vendedor +"'";
			this.statement.executeUpdate(query);
				
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void editarClienteVendedor (int id, int id_vendedor) {
		try {
			String query = "UPDATE cliente SET id_vendedor = '" + id_vendedor +"'"
							+ " WHERE id = '" + id +"'";
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void removerAssociacao (int id) {
		try {
			String query = "UPDATE cliente SET id_vendedor = NULL WHERE id = '" + id +"'";
			this.statement.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}
	
	public void selecionarClienteVendedor (int id_vendedor) {
		try {
			String query = "SELECT nome FROM cliente  WHERE id_vendedor = '" + id_vendedor +"'";
			this.resultset = this.statement.executeQuery(query);
			
			while (this.resultset.next()) {
				System.out.println("Clinte: " + this.resultset.getString("nome"));
			}
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}
}