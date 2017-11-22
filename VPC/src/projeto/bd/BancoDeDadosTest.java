package projeto.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;

public class BancoDeDadosTest {
	private Connection connection = null; //gerencia a conexão com o banco de dados
	private Statement statement = null;	//gerencia as consultas
	private ResultSet resultset = null;	//retorna um conjunto de informações do SELECT
	@Test
	public void testConectar() {
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

	@Ignore
	public boolean testEstaConectado() {

		if (this.connection != null) {
			return true;
		} else {
			return false;
		}
	}
	@Test
	public void testcriarTabelaCliente () {
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
	
	@Ignore
	public void testListarCliente() {
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

	@Ignore
	public void testInserirCliente(int id, String nome, String CPF, String endereco, String cidade,
			String cep, String fone, int id_vendedor) {
		try {
			String query = "INSERT INTO cliente (id, nome, CPF, endereco, cidade, cep, fone, id_vendedor)"
					+ "VALUES('" + id + "' , '" +nome+ "','" + CPF + "', '"+endereco+ "',"
					+ "'" + cidade + "', '"+ cep + "', '"+ fone +"', '"+ id_vendedor +"');";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}

	@Ignore
	public void testEditarCliente(String cpf, String nome, String endereco, String cidade,
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

	@Ignore
	public void testListarClienteId(int id) {
		try {
			String query = "SELECT * FROM cliente WHERE id = '" + id + "' ORDER BY nome";	//faz a consulta
			this.resultset = this.statement.executeQuery(query);	//recebe a consulta
		//ele roda enquanto houver itens a serem exibidos
			while (this.resultset.next()) {
				System.out.println("CPF: " + this.resultset.getString("cpf") + 
						" - Nome: " + this.resultset.getString("nome") +
						" - Endereço: " + this.resultset.getString("endereco") +
						" - Cidade: " + this.resultset.getString("cidade") + 
						" - Cep: " + this.resultset.getString("cep") + 
						" - Fone: " + this.resultset.getString("fone"));
			}
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}

	@Ignore
	public void testApagarCliente(String id) {
		
		try {
			String query = "DELETE FROM cliente WHERE ID = '" + id +"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
	}

	@Ignore
	public void testEditarClienteVendedor(int id, int id_vendedor) {
		try {
			String query = "UPDATE cliente SET id_vendedor = '" + id_vendedor +"'"
					+ " WHERE id = '" + id +"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}

	@Ignore
	public void testRemoverAssociacao(int id) {
		try {
			String query = "UPDATE cliente SET id_vendedor = NULL WHERE id = '" + id +"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	@Ignore
	public void testSelecionarClienteVendedor(int id_vendedor) {
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
	
	@Test
	public void TestcriarTabelaVendedor () {
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

	@Ignore
	public void testListarVendedor() {
		try {
			String query = "SELECT * FROM vendedor ORDER BY nome";	//faz a consulta
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

	@Ignore
	public void testInserirVendedor(int id_vendedor, String cpf, String nome, String fone) {
		try {
			String query = "INSERT INTO vendedor (id_vendedor, CPF, nome, fone)"
					+ "VALUES('" + id_vendedor + "','" + cpf + "', '" +nome+ "', '" +fone+ "');";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}		
	}

	@Ignore
	public void testEditarVendedor(String cpf, String nome, int id_vendedor) {
		try {
			String query = "UPDATE vendedor SET CPF = '" + cpf +"', nome = '" +nome+ "'"
					+ " WHERE id_vendedor = '" +id_vendedor+"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}	
	}

	@Ignore
	public void testApagarVendedor(String id_vendedor) {
		
		try {
			String query = "DELETE FROM vendedor WHERE ID = '" + id_vendedor +"'";
			this.statement.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

	@Ignore
	public void testListarVendedorId(int id_vendedor) {
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

}
