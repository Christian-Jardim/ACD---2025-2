import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe abstrata
abstract class Circuit {
    public abstract double getResistance();
}

// Resistor concreto
class Resistor extends Circuit {
    private double resistance;

    public Resistor(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public double getResistance() {
        return resistance;
    }
}

// Série
class Serial extends Circuit {
    private List<Circuit> components = new ArrayList<>();

    public void add(Circuit c) {
        components.add(c);
    }

    @Override
    public double getResistance() {
        double total = 0;
        for (Circuit c : components) {
            total += c.getResistance();
        }
        return total;
    }
}

// Paralelo
class Parallel extends Circuit {
    private List<Circuit> components = new ArrayList<>();

    public void add(Circuit c) {
        components.add(c);
    }

    @Override
    public double getResistance() {
        double sum = 0;
        for (Circuit c : components) {
            sum += 1.0 / c.getResistance();
        }
        return 1.0 / sum;
    }
}

// Programa principal
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculadora de Resistência Equivalente ===");
        System.out.print("Quantos resistores deseja adicionar? ");
        int n = scanner.nextInt();

        List<Resistor> resistores = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.print("Digite o valor do resistor R" + i + " (em ohms): ");
            double valor = scanner.nextDouble();
            resistores.add(new Resistor(valor));
        }

        System.out.print("O circuito é em Série (1) ou Paralelo (2)? ");
        int opcao = scanner.nextInt();

        Circuit circuito;
        if (opcao == 1) {
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

        System.out.println("Resistência equivalente: " + circuito.getResistance() + " ohms");

        scanner.close();
    }
}
