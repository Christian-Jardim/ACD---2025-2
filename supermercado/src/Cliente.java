public class Cliente {
	private String nome;
	private String CPF;

	public Cliente(String n, String cpf) {
		nome = n;
		CPF = cpf;
	}

	public String seApresentar() {
		return "\n Cliente -> Nome: " + nome + "\n\t    CPF: " + CPF;
	}
}
