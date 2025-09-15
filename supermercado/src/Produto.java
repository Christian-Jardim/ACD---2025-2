public class Produto {
	public Descricao descricao;
	public int tipo;
	public double preco;
	public int quantidadeEstoque;

	public Produto(int t, double v, int q) {
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

		tipo = t;
		preco = v;
		quantidadeEstoque = q;
	}

	public int intDescricao() {
		return tipo;
	}

	public String mostraProduto() {
		return "\n Produto -> Tipo: " + descricao + "\n\t    Quantidade: " + quantidadeEstoque + "\n\t    Preco: " + preco;
	}
}
