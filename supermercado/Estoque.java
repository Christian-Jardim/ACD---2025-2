import java.util.LinkedList;

public class Estoque {
	LinkedList<Produto> armazem;

	public Estoque() {
		armazem = new LinkedList<Produto>();
	}

	public void mostraEstoque() {
		for(Produto p : armazem) {
			System.out.println(p.mostraProduto());
		}
	}

	public void preenche(Produto produto) {
		armazem.add(produto);
	}
}
