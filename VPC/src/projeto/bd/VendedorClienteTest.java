package projeto.bd;

import org.junit.BeforeClass;

public class VendedorClienteTest {

	@BeforeClass
	public static void main (String [] args) { 
		BancoDeDados bancoDeDados = new BancoDeDados ();
		bancoDeDados.conectar();
		if (bancoDeDados.estaConectado()) {
			//bancoDeDados.inserirCliente(5, "Larlene CDS","12345648815", "R. Manoel de liveira", "Guarabira", "58200-000","83998020835", 2);
			//bancoDeDados.editarCliente("12345648815", "João broculos doido", "Rua joão alfredo", "João Pessoa", "12345-000", "0000-0000", 5);
			//bancoDeDados.apagarCliente("5");
			//bancoDeDados.listarCliente();
			//bancoDeDados.listarClienteId(5);
			//bancoDeDados.editarClienteVendedor(3, 2);
			//bancoDeDados.selecionarClienteVendedor(1);
			//bancoDeDados.removerAssociacao(3);
	
			
			//bancoDeDados.inserirVendedor(5, "10235478987", "Marcus", "83510515054");
			//bancoDeDados.editarVendedor("111.523.448-32", "João broculos doido", 5);
			//bancoDeDados.apagarVendedor("0");
			//bancoDeDados.listarVendedor();
			//bancoDeDados.listarVendedorId(5);
			
		} else {
			System.out.println("Não possível se conectar ao Banco de Dados!");
		}
	}

}
