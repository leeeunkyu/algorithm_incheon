package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

//13:05
public class Main_1339 {
	
	static int n;
	static char[][] arr;
	static char[][] words;
	static Point_1399[] points;
	static int size;
	static boolean[] visited;
	static int[] valMap;
	static int res = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		HashSet<Character> hs = new HashSet<>();
		arr = new char[n][n];
		int l = Integer.MIN_VALUE; 
		char val = 0;
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			arr[i] = str.toCharArray();
			if(arr[i].length > l) {
				l = arr[i].length;
				val = arr[i][0];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				hs.add(arr[i][j]);
			}
		}
		size = hs.size();
		words = new char [size][size];
		points = new Point_1399[size];
		for (int i = 0; i < size; i++) {
			points[i] = new Point_1399();
		}
		visited = new boolean[10];
		valMap = new int[size];
		
		int i = 0;
		for (char a: hs) {
			//words[i][0] = a;
			points[i].setC(a);
			if(val == a) {
				//a = words[0][0];
				a = points[0].getC();
				//words[0][0] = a;
				points[0].setC(val);
				points[i].setC(a);
			}
			i++;
		}
		//words[0][1] = 9;
		points[0].setNum(9);
		dfs(1);
		System.out.println(res);
	}

	private static void dfs(int cnt) {
		if(cnt == size) {
			for (int i = 0; i < size; i++) {
				Point_1399 point = points[i];
				char c = point.getC();
				int num = point.getNum();
				valMap[c - 'A'] = num;
			}
			String str = "";
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum = 0;
				str = "";
				for (int j = 0; j < arr[i].length; j++) {
					char c = arr[i][j];
					int val = valMap[c - 'A'];
					str += val;
				}
				sum += Integer.parseInt(str);
			}
			if(res < sum)
				res = sum;
			return;
		}
		
		for (int i = cnt; i < size; i++) {
			for (int j = 8 - (size - 2); j < 9; j++) {
				//words[i][1] = 8 to 8 - (size - 1)...	
				//words[i][1] = (char) j;
				if(visited[j])
					continue;
				points[i].setNum(j);
				visited[j] = true;
				dfs(cnt + 1);
				visited[j] = false;
			}
		}
	}
}

class Point_1399 {
	char c;
	int num;
	
	public Point_1399(char c, int num) {
		super();
		this.c = c;
		this.num = num;
	}
	
	public Point_1399() {
		super();
	}

	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}