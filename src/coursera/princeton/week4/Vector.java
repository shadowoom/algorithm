package coursera.princeton.week4;

public final class Vector implements Comparable<Vector>{
	
	private final int N;
	
	private final double[] data;
	
	public Vector(double[] data) {
		this.N = data.length;
		this.data = new double[N];
		for(int i = 0; i < N; i++) {
			this.data[i] = data[i];
		}
	}

	@Override
	public int compareTo(Vector o) {
		for(int i = 0; i < N; i++) {
			if(this.data[i] < o.data[i])
				return -1;
			else if(this.data[i] > o.data[i])
				return 1;
			else
				continue;
		}
		return 0;
	}
	

}
