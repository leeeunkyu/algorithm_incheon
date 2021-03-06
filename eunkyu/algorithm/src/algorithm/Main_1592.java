/*package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_1592 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int m = Integer.parseInt(str[1]);
		int l = Integer.parseInt(str[2]);
		Deque<People> dq = new LinkedList<People>();
		for (int i = 1; i <= n; i++) {
			dq.add(new People(i, 0));
		}
		ballGame(dq, n, m, l);
	}

	private static void ballGame(Deque<People> dq, int n, int m, int l) {
		int cnt = 0;
		People people = dq.pollFirst();
		while(true) {
			int ball = people.getBall() + 1;
			people.setBall(ball);
			
			for (int i = 0; i < l; i++) {
				if(ball % 2 == 0) {
					//¦���� �ݽð�
					dq.addFirst(people);
					people = dq.pollLast();
				} else {
					//Ȧ���� �ð�
					dq.addLast(people);
					people = dq.pollFirst();
				}
			}
			if(ball == m)
				break;
			cnt++;
			
		}
		System.out.println(cnt);
	}
}

class People {
	
	private int num;
	private int ball;
	
	public People(int num, int ball) {
		super();
		this.num = num;
		this.ball = ball;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBall() {
		return ball;
	}
	public void setBall(int ball) {
		this.ball = ball;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("People [num=");
		builder.append(num);
		builder.append(", ball=");
		builder.append(ball);
		builder.append("]");
		return builder.toString();
	}
	
	
}
*/