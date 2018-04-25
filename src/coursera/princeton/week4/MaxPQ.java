package coursera.princeton.week4;

import java.util.NoSuchElementException;

public class MaxPQ {
	
	private Vector[] pq;
	private int N;

	public MaxPQ() {
		pq = new Vector[1];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}
	
	public void insert(Vector x) {
		if(N == pq.length)
			resize(2 * pq.length);
		pq[N++] = x;
		swim(N);
	}
	
	public Vector delMax() {
		if(N == 0)
			throw new NoSuchElementException();
		Vector max = pq[0];
		exch(1, N--);
		sink(1);
		pq[N] = null;
		if(N == pq.length / 4)
			resize(pq.length / 2);
		return max;
 	}
	
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2*k <= N) {
			int j = 2*k;
			if(j < N && less(j, j+1))
				j++;
			if(!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
 	private boolean less(int i, int j) {
 		return pq[i-1].compareTo(pq[j-1]) >= 0? false:true;
 	}

 	private void exch(int i, int j) {
 		Vector temp = pq[i-1];
 		pq[i-1] = pq[j-1];
 		pq[j-1] = temp;
 	}

	private void resize(int capacity) {
		Vector[] copy = (Vector[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = pq[i];
		}
		pq = copy;
	}
}
