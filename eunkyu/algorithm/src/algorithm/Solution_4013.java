package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution_4013 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < testCase; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			Gear gear[] = new Gear[4];
			GearMeet[] gearMeet = new GearMeet[4];

			int k = Integer.parseInt(br.readLine());
			
			for (int j = 0; j < gear.length; j++) {
				String str[] = br.readLine().split(" ");
				Deque<Integer> dq = new LinkedList<Integer>();
				for (int l = 0; l < str.length; l++) {
					dq.add(Integer.parseInt(str[l]));
				}	
				gear[j] = new Gear(dq);
			}
			
			String rInfo[] = new String[k];
			for (int j = 0; j < k; j++) {
				rInfo[j] = br.readLine();
			}
			
			intit(gear, gearMeet);
			rotate(gear, gearMeet, rInfo);
			print(gear, gearMeet);
		}
	}

	private static void rotate(Gear[] gear, GearMeet[] gearMeet, String[] rInfo) {
		for (int i = 0; i < rInfo.length; i++) {
			String str[] = rInfo[0].split(" ");
			int gearNum = Integer.parseInt(str[0]);
			int dir = Integer.parseInt(str[1]);
			boolean check = gearMeet[1].getRight() == gearMeet[2].getLeft();
			for (int d = 0; d < gearMeet.length; d++) {
				//dir + d = 1, 2, 3, 4
				int nearDir = (gearNum - 1) % 2;

				if(gearMeet[d].getRight() != gearMeet[d].getLeft()) {
					
					if(nearDir == 0) {
						if(dir == 1) {
							System.out.println("??");
							rRotate(gear, gearMeet, d);
						}else {
							System.out.println("??");
							lRotate(gear, gearMeet, d);
						}
					}else {
						if(dir == 1) {
							System.out.println("??");
							lRotate(gear, gearMeet, d);
						}else {
							System.out.println("??");
							rRotate(gear, gearMeet, d);
						}
					}
				}
			}
			intit(gear, gearMeet);
		}
		
	}

	
	
	private static void lRotate(Gear[] gear, GearMeet[] gearMeet, int gearNum) {
		Deque<Integer> dq = gear[gearNum].getDq();
		int val = dq.pollFirst();
		dq.addLast(val);	
	}

	private static void rRotate(Gear[] gear, GearMeet[] gearMeet, int gearNum) {
		Deque<Integer> dq = gear[gearNum].getDq();
		int val = dq.pollLast();
		dq.addFirst(val);
	}

	private static void print(Gear[] gear, GearMeet[] gearMeet) {
		for (int i = 0; i < gear.length; i++) {
			System.out.println(gear[i].getDq());
		}
		System.out.println("========================");
	}

	private static void intit(Gear[] gear, GearMeet[] gearMeet) {
		for (int i = 0; i < gear.length; i++) {
			int left = 0;
			int right = 0;
			
			Deque<Integer> dq = gear[i].getDq();
			Stack<Integer> stack = new Stack<>();
			
			for (int j = 0; j < 7; j++) {
				int val = dq.pop();
				stack.add(val);
				if(j == 2)
					right = val;
				if(j == 6)
					left = val;
			}
			
			gearMeet[i] = new GearMeet(left, right);
			while (!stack.isEmpty()) {
				dq.addFirst(stack.pop());				
			}
		}
	}
}

class Gear {
	//8개 날
	//시계방향으로 돌릴경우 맨 뒤에께 맨 앞으로
	//반시계방향으로 돌릴경우 앞에게 맨 뒤로
	Deque<Integer> dq;

	public Gear(Deque<Integer> dq) {
		super();
		this.dq = dq;
	}

	public Deque<Integer> getDq() {
		return dq;
	}

	public void setDq(Deque<Integer> dq) {
		this.dq = dq;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gear [dq=");
		builder.append(dq);
		builder.append("]");
		return builder.toString();
	}
	
}

class GearMeet {
	//톱니바퀴 양끝에 대한 정보
	int left;
	int right;
	
	public GearMeet(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GearMeet [left=");
		builder.append(left);
		builder.append(", right=");
		builder.append(right);
		builder.append("]");
		return builder.toString();
	}
	
}
