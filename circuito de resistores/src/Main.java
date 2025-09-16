import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int op1,op2;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("\n 1 - Adicionar circuito\n 0 - Sair\n Escolha uma opcao: "); // Opcao para sair ou adicionar circuito
			op1 = scanner.nextInt();

			if(op1 == 1) {
				System.out.print("\n Quantos resistores deseja adicionar? "); // Recebe a quantidade de resistores
				int quant = scanner.nextInt();

				ArrayList<Resistor> resistores = new ArrayList<>();
				for (int i = 1; i <= quant; i++) { // Loop para preencher o circuito
					System.out.print(" Digite o valor do resistor R" + i + " (em ohms): "); // Recebe o valor (em ohms) de cada resistor
					double value = scanner.nextDouble();
					resistores.add(new Resistor(value));
				}

				System.out.print("\n O circuito e em Serie (1) ou Paralelo (2)? "); // Usuario decide se sera em serie ou paralelo
				op2 = scanner.nextInt();

				Circuit circuito;

				if (op2 == 1) { // calcula a resistencia equivalente para o circuito em serie
					Serial serie = new Serial();
					for (Resistor r : resistores) {
						serie.add(r);
					}
					circuito = serie;
				} 
				else if(op2 == 2) { // calcula a resistencia equivalente para o circuito em paralelo
					Parallel paralelo = new Parallel();
					for (Resistor r : resistores) {
						paralelo.add(r);
					}
					circuito = paralelo;
				} else {
					System.out.print("\n Opcao invalida!");
				}
				// Mostra a resistencia equivalente de acordo com o tipo de circuito escolhido
				System.out.println("\n Resistencia equivalente: " + circuito.getResistance() + " ohms");
			} else {
				System.out.print("\n Opcao invalida!");
			}
		} while(op1 != 0);
	}
}
