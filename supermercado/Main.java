import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int op;
		Cliente cliente;
		Scanner input = new Scanner(System.in);
		Pedido pedido = new Pedido();
		Estoque estoque = new Estoque();

		do {
			op = menu(input);
			switch(op) {
			case 1:
				if(estoque.armazem.isEmpty()) {
					System.out.println("\n Estoque vazio!");
				} else {
					cliente = recebeCliente(input);                     // Recebe as informacoes do cliente
					System.out.println(cliente.seApresentar());
					recebePedido(input,pedido,estoque);                 // Recebe os itens do pedido
				}
				break;
			case 2:
				if(pedido.itens.isEmpty()) {
					System.out.println("\n Nao ha pedido feito!!");
				} else {
					pagar(input, pedido, estoque);                      // Recebe o metodo de pagamento e o realiza
				}
				break;
			case 3:
				recebeProduto(input, estoque);                          // Recebe e cadastra produtos para o estoque
				break;
			case 4:
				if(estoque.armazem.isEmpty()) {
					System.out.println("\n Estoque vazio!");
				} else {
					estoque.mostraEstoque();                            // Mostra o estoque
				}
				break;
			case 5:
				if(pedido.itens.isEmpty()) {
					System.out.println("\n Nao ha pedido feito!!");
				} else {
					pedido.mostraPedido();                              // Mostra a lista de itens do pedido
				}
			}
		} while (op != 0);
	}

	public static int menu(Scanner input) {
		int op;
		// Recebe a opcao e a devolve para o main
		do {
			System.out.println("\n 1) Novo pedido\n 2) Realizar pagamento\n 3) Cadastrar um produto\n 4) Mostrar armazem\n 5) Mostrar pedido\n 0) Sair da aplicacao");
			System.out.print(" Escolha uma das opcoes: ");
			op = input.nextInt();
			input.nextLine();
		} while(op<0 || op>5);

		return op;
	}

	public static Cliente recebeCliente(Scanner input) {
		System.out.print("\n Cliente:\n Digite o nome do Cliente: ");
		String nome = input.nextLine();
		System.out.print(" Digite o CPF do cliente: ");
		String cpf = input.nextLine();
		return (new Cliente(nome, cpf)); // Retorna para o main um novo cliente
	}

	public static void recebeProduto(Scanner input, Estoque estoque) {
		int x = 0;

		System.out.print("\n Produto:\n Digite o codigo do produto (1-arroz 2-feijao 3-farinha 4-leite): ");
		int tipo = input.nextInt();

		if(tipo<1 | tipo>4) { // Verifica se o codigo e valido
			System.out.println("\n Codigo invalido!");
		} else {
			System.out.print(" Digite a quantidade: ");
			int quant = input.nextInt();

			for (Produto p : estoque.armazem) {
				if (p.intDescricao() == tipo) { // Verifica se o produto ja esta no estoque, somando a nova quantidade a atual se sim
					p.quantidadeEstoque += quant;
					System.out.println("\n Produto ja no estoque! A nova quantidade foi somada!");
					x = 1;
				}
			}

			if(x != 1) { // Caso o produto nao esteja no estoque, recebe o seu valor e o adicina a ele
				System.out.print(" Digite o valor: ");
				int valor = input.nextInt();
				estoque.preenche(new Produto(tipo, valor, quant));
			}
		}
	}

	public static void recebePedido(Scanner input, Pedido pedido, Estoque estoque) {
		int x,y,i;
		x = i = y = 0;

		System.out.print("\n Informe a quantidade de tipos de itens do pedido: ");
		x = input.nextInt();

		if(x<estoque.armazem.size() | x>estoque.armazem.size()) { // Verifica se a quantidade de tipos de itens e valida
			System.out.println("\n A quantidade de tipos de itens do armazem e: " + estoque.armazem.size());
		} else {
			while(i<x) { // Loop para receber os itens do pedido
				System.out.print("\n Digite o codigo do item (" + (i + 1) + ") (1-arroz 2-feijao 3-farinha 4-leite): ");
				int tipo = input.nextInt();

				for(Item item : pedido.itens) { // Verifica se o item ja esta no pedido
					if(tipo == item.intDescricao()) {
						System.out.println("\n O item ja esta no pedido!");
						y = 1;
					}
				}

				if(y != 1) { // Se o item nao estiver no pedido, recebe a sua quantidade
					System.out.print(" Digite a quantidade dele: ");
					int quantidade = input.nextInt();
					
					for(Produto p : estoque.armazem) {
						if(p.tipo == tipo & p.quantidadeEstoque < quantidade) { // Verifica o item no armazem e se o estoque nao e suficiente
							System.out.println("\n Quantidade de estoque do item insuficiente!");
							i--;
						}
						else { // Se o estoque for suficiente, adiciona o item ao pedido
							pedido.preenche(new Item(quantidade, tipo));
							break;
						}
					}
				}

				y = 0;
				i++;
			}
		}
	}

	public static void pagar(Scanner input, Pedido pedido, Estoque estoque) {
		double vt = 0 ;
		
		System.out.print("\n Informe o meio de pagamento (1-dinheiro 2-cheque 3- cartao): ");
		int mp = input.nextInt();

		if(mp<1 | mp>4) { // Verifica se o meio de pagamento nao e valido
			System.out.println("\n Metodo invalido!");
		} else {
			pedido.modoPagamento(mp);

			for(Produto produto : estoque.armazem) { // Loop para calcular bo valor total do pedido
				for(Item item : pedido.itens) {
					if(produto.descricao == item.descricao) {
						produto.quantidadeEstoque -= item.quantidade;
						vt += produto.preco * item.quantidade;
					}
				}
			}

			System.out.println("\n ### PEDIDO ###"); // Mostra as informacoes do pedido
			pedido.mostraPedido();
			System.out.println("\n Meio de pagamento: " + pedido.getPagamento());
			System.out.println(" Valor total do pedido: " + vt);
		}
	}
}
