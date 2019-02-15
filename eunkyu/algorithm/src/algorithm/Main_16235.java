package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main_16235 {
	static int n;
	static int m;
	//r �� c�� 1���� ����
	//����� ��� ĭ�� 5��ŭ
	//m���� ������ ����
	//K���� ���� �� ���� ���� ����ִ� ������ ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		int k = Integer.parseInt(str[2]);
		int[][] arr = new int [n + 1][n + 1];
		int[][] nutrition = new int [n + 1][n + 1];
		PriorityQueue<Tree> treeQueue = new PriorityQueue<>();
		Queue<Tree> deadQueue = new LinkedList<Tree>();
		for (int i = 1; i <= n; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 1; j <= n; j++) {
				nutrition[i][j] = Integer.parseInt(str2[j - 1]);
				arr[i][j] = 5;
			}
		}
		for (int i = 0; i < m; i++) {
			String[] str2 = br.readLine().split(" ");
			int r = Integer.parseInt(str2[0]); //r ���������� �������� ���� �� 
			int c = Integer.parseInt(str2[1]); //c ���ʿ��� ���� �������°��� �� 
			treeQueue.add(new Tree(
							c, 
							r, 
							Integer.parseInt(str2[2])));
		}
		new Main_16235().init(arr, nutrition, treeQueue, deadQueue, k);
	}
	
	private void init(int[][] arr, int[][] nutrition, PriorityQueue<Tree> treeQueue, Queue<Tree> deadQueue, int k) {
		for (int i = 0; i < k; i++) {
			spring(arr, treeQueue, deadQueue);
			summer(arr, treeQueue, deadQueue);
			fall(arr, treeQueue);
			winter(arr, nutrition);
		}
		System.out.println(treeQueue.size());
	}

	/*
	 * ������ ������ �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1 �����Ѵ�. 
	 * ������ ������ ������ �ִ� 1��1 ũ���� ĭ�� �ִ� ��и� ���� �� �ִ�. 
	 * �ϳ��� ĭ�� ���� ���� ������ �ִٸ�, ���̰� � �������� ����� �Դ´�.-> ���̼������ �̸�����
	 * ����, ���� ����� ������ �ڽ��� ���̸�ŭ ����� ���� �� ���� ������ 
	 * ����� ���� ���ϰ� ��� �״´�.
	 */
	private void spring(int[][] arr, PriorityQueue<Tree> treeQueue, Queue<Tree> deadQueue) {
		Queue<Tree> tempQueue = new LinkedList<Tree>();
		while (!treeQueue.isEmpty()) {
			Tree tree = treeQueue.poll();
			int x = tree.getX();
			int y = tree.getY();
			int age = tree.getAge();
			if(age <= arr[y][x]) {
				arr[y][x] -= age;
				tree.setAge(age + 1);
				tempQueue.add(tree);
			} else {
				deadQueue.add(tree);
			}
		}
		while (!tempQueue.isEmpty()) {
			treeQueue.add(tempQueue.poll());
		}
	}	
	
	/*
	 * �������� ���� ���� ������ ������� ���ϰ� �ȴ�. 
	 * ������ ���� �������� ���̸� 2�� ���� ���� ������ �ִ� ĭ�� ������� �߰��ȴ�. 
	 * �Ҽ��� �Ʒ��� ������.
	 */
	private void summer(int[][] arr, PriorityQueue<Tree> treeQueue, Queue<Tree> deadQueue) {
		while (!deadQueue.isEmpty()) {
			Tree tree = deadQueue.poll();
			int x = tree.getX();
			int y = tree.getY();
			int age = tree.getAge();
			arr[y][x] += (age / 2);
		}
	}
	
	/*
	 * �������� ������ �����Ѵ�. �����ϴ� ������ ���̰� 5�� ����̾�� �ϸ�, 
	 * ������ 8���� ĭ�� ���̰� 1�� ������ �����. 
	 * � ĭ (r, c)�� ������ ĭ�� (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) �̴�. 
	 * ���� ���� ����� ĭ���� ������ ������ �ʴ´�.
	 */
	private void fall(int[][] arr, PriorityQueue<Tree> treeQueue) {
		Queue<Tree> tempQueue = new LinkedList<Tree>();

		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		int size = treeQueue.size();
		
		for (int idx = 0; idx < size; idx++) {
			Tree tree = treeQueue.poll();
			tempQueue.add(tree);
			int x = tree.getX();
			int y = tree.getY();
			int age = tree.getAge();
			if(age % 5 != 0) {
				continue;
			}
			
			for (int i = 0; i < 8; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= n) {
					tempQueue.add(new Tree(nextX, nextY, 1));
				}
			}
		}
		while (!tempQueue.isEmpty()) {
			treeQueue.add(tempQueue.poll());
		}
	}
	/*
	 * �ܿ￡�� S2D2�� ���� ���ƴٴϸ鼭 ���� ����� �߰��Ѵ�. 
	 * �� ĭ�� �߰��Ǵ� ����� ���� A[r][c]�̰�, �Է����� �־�����.
	 */
	private void winter(int[][] arr, int[][] nutrition) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				arr[i][j] += nutrition[i][j];
			}
		}
	}
}


class Tree implements Comparable<Tree>{
	
	private int x;
	private int y;
	private int age;
	
	public Tree(int x, int y, int age) {
		super();
		this.x = x;
		this.y = y;
		this.age = age;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tree [age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Tree tree) {
		return this.age - tree.getAge();
	}
}