package ClassicAlgorithm;

import java.util.Comparator;

public class MaxPriorityQueue<Key extends Comparable<Key>> {

	private Key[] pq;
	private int N;

	public MaxPriorityQueue(int maxSize) {
		pq = (Key[]) new Comparable[maxSize + 1];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exchange(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exchange(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1)) {
				j++;
			}
			if (!less(k, j)) {
				break;
			}
			exchange(k, j);
			k = j;
		}
	}

	public void insert(Key value) {
		pq[++N] = value;
		swim(N);
	}

	public Key delMax() {
		Key max = pq[1];
		exchange(1, N--);
		pq[N + 1] = null;
		sink(1);
		return max;
	}

	@Deprecated
	public void printQueue() {
		for (int i = 1; i<= N; i++) {
			System.out.print(pq[i].toString()+" ");
		}
		System.out.println("");
	}
}
