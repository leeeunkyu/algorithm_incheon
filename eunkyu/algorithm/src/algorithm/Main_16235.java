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
	//r 과 c는 1부터 시작
	//양분은 모든 칸이 5만큼
	//m개의 나무를 구매
	//K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성하시오.
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
			int r = Integer.parseInt(str2[0]); //r 위에서부터 떨어지는 갯수 행 
			int c = Integer.parseInt(str2[1]); //c 왼쪽에서 부터 떨어지는갯수 열 
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
	 * 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 
	 * 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다. 
	 * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.-> 나이순서대로 미리정렬
	 * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 
	 * 양분을 먹지 못하고 즉시 죽는다.
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
	 * 여름에는 봄에 죽은 나무가 양분으로 변하게 된다. 
	 * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 
	 * 소수점 아래는 버린다.
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
	 * 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 
	 * 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 
	 * 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다. 
	 * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
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
	 * 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 
	 * 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
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
