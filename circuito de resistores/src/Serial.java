import java.util.ArrayList;

public class Serial extends Circuit {
	private ArrayList<Circuit> components = new ArrayList<>();

	public void add(Circuit c) {
		components.add(c);
	}

	public double getResistance() {
		double total = 0;
		for (Circuit c : components) {
			total += c.getResistance();
		}
		return total;
	}
}