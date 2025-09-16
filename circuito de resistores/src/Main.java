import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int op1,op2;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("\n 1 - Adicionar circuito\n 0 - Sair\n Escolha uma opcao: ");
			op1 = scanner.nextInt();

			if(op1 != 0) {
				System.out.print("\n Quantos resistores deseja adicionar? ");
				int quant = scanner.nextInt();

				ArrayList<Resistor> resistores = new ArrayList<>();
				for (int i = 1; i <= quant; i++) {
					System.out.print(" Digite o valor do resistor R" + i + " (em ohms): ");
					double value = scanner.nextDouble();
					resistores.add(new Resistor(value));
				}

				System.out.print("\n O circuito e em Serie (1) ou Paralelo (2)? ");
				op2 = scanner.nextInt();

				Circuit circuito;

				if (op2 == 1) {
					Serial serie = new Serial();
					for (Resistor r : resistores) {
						serie.add(r);
					}
					circuito = serie;
				} else {
					Parallel paralelo = new Parallel();
					for (Resistor r : resistores) {
						paralelo.add(r);
					}
					circuito = paralelo;
				}

				System.out.println("\n Resistencia equivalente: " + circuito.getResistance() + " ohms");
			}
		} while(op1 != 0);
	}
}
