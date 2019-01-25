package algorithm;

import java.util.PriorityQueue;

public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<SharkTest> q = new PriorityQueue<SharkTest>();
		q.add(new SharkTest(1, 1, 1, 1));
		System.out.println(q.peek().toString());
		q.add(new SharkTest(1, 1, 1, 2));
		System.out.println(q.peek().toString());
		q.add(new SharkTest(1, 1, 1, 3));
		q.add(new SharkTest(1, 1, 1, 4));
		q.add(new SharkTest(1, 1, 1, 5));
		q.add(new SharkTest(1, 1, 1, 6));
		System.out.println(q.peek().toString());
		q.poll();
		System.out.println(q.peek().toString()); //add하는 순간 정렬
	}
}

class SharkTest implements Comparable<SharkTest>{
	
	int x;
	int y;
	static int size;
	int order;
	
	public SharkTest(int x, int y, int size, int order) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.order = order;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int compareTo(SharkTest o) {
		return o.getOrder() - this.getOrder(); //내림 차순 정렬
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SharkTest [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}
}