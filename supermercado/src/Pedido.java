import java.util.LinkedList;

public class Pedido {
	public TipoPagamento pagamento;
	public LinkedList<Item> itens;

	public Pedido() {
		itens = new LinkedList<Item>();
	}

	public void modoPagamento(int modo) {
		switch(modo) {
		case 1:
			pagamento = TipoPagamento.DINHEIRO;
			break;
		case 2:
			pagamento = TipoPagamento.CHEQUE;
			break;
		case 3:
			pagamento = TipoPagamento.CARTAO;
			break;
		}
	}

	public void preenche(Item item) {
		itens.add(item);
	}

	public TipoPagamento getPagamento() {
		return pagamento;
	}

	public void mostraPedido() {
		for (Item item : itens) {
			System.out.println(item.mostraItem());
		}
	}
}
