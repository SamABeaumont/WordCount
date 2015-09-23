public class Frequency implements Comparable<Frequency> {
	private String s;
	private int n;
	
	public Frequency () {
		this("", 0);
	}
	
	public Frequency (String iS, int iN) {
		s = iS;
		n = iN;
	}
	
	public String getString () {
		return s;
	}
	
	public int getN () {
		return n;
	}
	
	public void increment () {
		n++;
	}
	
	public String toString () {
		return s + " - " + n;
	}
	
	public int compareTo (Frequency other) {
		if (n < other.getN()) {
			return -1;
		} else if (n == other.getN()) {
			return 0;
		} else {
			return 1;
		}
	}
}