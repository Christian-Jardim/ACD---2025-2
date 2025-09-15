public class Item {
	public int quantidade;
	public int tipo;
	public Descricao descricao;

	public Item(int q, int t) {
		quantidade = q;
		tipo = t;

		switch(t) {
		case 1:
			descricao = Descricao.ARROZ;
			break;
		case 2:
			descricao = Descricao.FEIJAO;
			break;
		case 3:
			descricao = Descricao.FARINHA;
			break;
		case 4:
			descricao = Descricao.LEITE;
			break;
		}
	}

	public int intDescricao() {
		return tipo;
	}

	public String mostraItem() {
		return "\n Item -> Tipo: " + descricao + "\n\t Quantidade: " + quantidade;
	}
}
