package projeto.bd;



public class VendedorCliente {
	
	public static void main (String [] args) { 
		BancoDeDados bancoDeDados = new BancoDeDados ();
		bancoDeDados.conectar();
		if (bancoDeDados.estaConectado()) {
			//bancoDeDados.criarTabelaVendedor();
			//bancoDeDados.inserirVendedor(2, "Juliana", "021.548.799-55", "8398456897");
			//bancoDeDados.editarVendedor("111.523.448-32", "Mano Valter", 2);
			//bancoDeDados.apagarVendedor(2);
			//bancoDeDados.listarVendedor();
			//bancoDeDados.listarVendedorId(2);
			
			//bancoDeDados.criarTabelaCliente();
			//bancoDeDados.inserirCliente(2, "Maria","441.496.550-30", "R. dos professores, 09 ", "JAMPA", "51200-000","8398020835", 1);
			//bancoDeDados.editarCliente("12345648815", "João broculos doido", "Rua joão alfredo", "João Pessoa", "12345-000", "0000-0000", 2);
			//bancoDeDados.apagarCliente(2);
			//bancoDeDados.listarCliente();
			//bancoDeDados.listarClienteId(1);
			//bancoDeDados.editarClienteVendedor(1, 2);
			//bancoDeDados.selecionarClienteVendedor(1);
			bancoDeDados.removerAssociacao(1);
	
			
			
			
		} else {
			System.out.println("Não possível se conectar ao Banco de Dados!");
		}
	}
}