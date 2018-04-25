package coursera.princeton.week4;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class UnorderedMaxPQ implements Iterable<Vector>
{
	private Vector[] pq;
	private int N;

	public UnorderedMaxPQ() {
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
	}

	public Vector delMax() {
		if(N == 0)
			throw new NoSuchElementException();
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(less(pq[max], pq[i]))
				max = i;
		}
		exch(max, N-1);
		Vector vector = pq[--N];
		pq[N] = null;
		if(N == pq.length / 4)
			resize(pq.length / 2);
		return vector;

 	}

 	private boolean less(Vector a, Vector b) {
 		return a.compareTo(b) >= 0? false:true;
 	}

 	private void exch(int i, int j) {
 		Vector temp = pq[i];
 		pq[i] = pq[j];
 		pq[j] = temp;
 	}

	private void resize(int capacity) {
		Vector[] copy = (Vector[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = pq[i];
		}
		pq = copy;
	}

	public Iterator<Vector> iterator() {
		return new MaxPQIterator();
	}

	private class MaxPQIterator implements Iterator<Vector> {
		
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public void remove() throws UnsupportedOperationException {
			/* not supported */
		}

		public Vector next() throws NoSuchElementException {
			return pq[--i];
		}
	}

}