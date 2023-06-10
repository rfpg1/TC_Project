package utils;

public class Triple<F, S, K> {

	private F first;
	private S second;
	private K third;
	
	public Triple(F first, S second, K third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public F getFirst() {
		return first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}
	
	public K getThird() {
		return third;
	}
	
	public void setThird(K third) {
		this.third = third;
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}
}
