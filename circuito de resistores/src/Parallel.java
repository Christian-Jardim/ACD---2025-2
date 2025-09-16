import java.util.ArrayList;

public class Parallel extends Circuit {
	private ArrayList<Circuit> components = new ArrayList<>();

	public void add(Circuit c) {
		components.add(c);
	}

	public double getResistance() {
		double sum = 0;
		for (Circuit c : components) {
			sum += 1.0 / c.getResistance();
		}
		return 1.0 / sum;
	}
}