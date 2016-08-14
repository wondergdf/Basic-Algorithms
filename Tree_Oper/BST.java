
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int N;

		public Node(Key key, Value value, int n) {
			this.key = key;
			this.value = value;
			this.N = n;
		}
	}

	private Node root;

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.N;
		}
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null) {
			return null;
		} else {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				return get(x.left, key);
			else if (cmp > 0)
				return get(x.right, key);
			else
				return x.value;
		}
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		return max(x.right);
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.left == null) {
				return x.right;
			}
			if (x.right == null) {
				return x.left;
			}
			Node t = x;
			x = min(x.right);
			x.left = t.left;
			x.right = deleteMin(t.right);

		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Iterator<Key> keys() {
		return keys(min(), max());
	}

	private Iterator<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new LinkedList<Key>();
		Keys(root, queue, lo, hi);
		return queue.iterator();
	}

	private void Keys(Node x, Queue queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0) {
			Keys(x.left, queue, lo, hi);
		}
		if (cmplo <= 0 && cmphi >= 0) {
			queue.add(x.key);
		}
		if (cmphi > 0) {
			Keys(x.right, queue, lo, hi);
		}

	}

	public void Reverse() {
		Reverse(root);
	}

	private void Reverse(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node != null) {
				Node temp;
				if (node.left != null && node.right != null) {
					temp = node.right;
					node.right = node.left;
					node.left = temp;
					queue.add(node.left);
					queue.add(node.right);

				} else if (node.left == null && node.right != null) {
					node.left = node.right;
					node.right = null;
					queue.add(node.left);

				} else if (node.right == null && node.left != null) {
					node.right = node.left;
					node.left = null;
                    queue.add(node.right);
				} else {
					continue;
				}
			}
		}

	}

	public static void main(String[] args) {
		BST<Integer, String> testBST = new BST<Integer, String>();

		testBST.put(3, "three");
		testBST.put(5, "five");
		testBST.put(2, "two");
		testBST.put(9, "nine");
		testBST.put(1, "one");
		testBST.put(8, "eight");
		testBST.put(4, "four");

		System.out.println("---Print info: -------------------");
		System.out.println("The Min Item: " + testBST.min());
		System.out.println("The Max Item:" + testBST.max());

		Iterator<Integer> iterator = testBST.keys();

		System.out.println("---Print the Key set: -------------------");
		while (iterator.hasNext()) {
			System.out.print("-" + iterator.next() + "-");
		}

		System.out.println();

		testBST.deleteMin();
		System.out
				.println("---After remove the previous Min item, the Current Min Item : ----");
		System.out.println(testBST.min());
		
		System.out.println("---Test Reverse binary tree func: ---------------");
		testBST.Reverse();
		System.out.println(testBST.root.left.left.value);
		testBST.Reverse();
		System.out.println(testBST.root.left.value);
		

	}
}
