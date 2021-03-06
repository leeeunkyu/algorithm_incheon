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
		
		for (int i = 1; i <= testCase; i++) {
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
			int sum = sumTop(gear);
			sb.append("#"+ i + " " + sum+"\n");
		}
		System.out.println(sb);
	}
	
	private static int sumTop(Gear[] gear) {
		int sum = 0;
		for (int i = 0; i < gear.length; i++) {
			Deque<Integer> dq = gear[i].getDq();
			int val = dq.pollFirst();
			if(val == 1) {
				if(i == 0)
					sum += 1;
				if(i == 1)
					sum += 2;
				if(i == 2)
					sum += 4;
				if(i == 3)
					sum += 8;
			}
		}
		
		return sum;
	}

	private static void rotate(Gear[] gear, GearMeet[] gearMeet, String[] rInfo) {
		for (int i = 0; i < rInfo.length; i++) {
			String str[] = rInfo[i].split(" ");
			int gearNum = Integer.parseInt(str[0]) - 1;
			int dir = Integer.parseInt(str[1]);
			int n = gear.length;
			if(dir == 1) {
				rRotate(gear, gearMeet, gearNum);
				//왼쪽 보기
				if(gearNum - 1>= 0 && gearMeet[gearNum].left != gearMeet[gearNum - 1].right) {
					lRotate(gear, gearMeet, gearNum - 1);
					if(gearNum - 2>= 0 && gearMeet[gearNum - 1].left != gearMeet[gearNum - 2].right) {
						rRotate(gear, gearMeet, gearNum - 2);
						if(gearNum - 3>= 0 && gearMeet[gearNum - 2].left != gearMeet[gearNum - 3].right) {
							lRotate(gear, gearMeet, gearNum - 3);
						}
					}
				}
				//오른쪽 보기
				if(gearNum + 1 < n && gearMeet[gearNum].right != gearMeet[gearNum + 1].left) {
					lRotate(gear, gearMeet, gearNum + 1);
					if(gearNum + 2 < n && gearMeet[gearNum + 1].right != gearMeet[gearNum + 2].left) {
						rRotate(gear, gearMeet, gearNum + 2);
						if(gearNum + 3 < n && gearMeet[gearNum + 2].right != gearMeet[gearNum + 3].left) {
							lRotate(gear, gearMeet, gearNum +3);
						}
					}
				}
			}else {
				lRotate(gear, gearMeet, gearNum);
				//왼쪽보기
				if(gearNum - 1>= 0 && gearMeet[gearNum].left != gearMeet[gearNum - 1].right) {
					rRotate(gear, gearMeet, gearNum - 1);
					if(gearNum - 2>= 0 && gearMeet[gearNum - 1].left != gearMeet[gearNum - 2].right) {
						lRotate(gear, gearMeet, gearNum - 2);
						if(gearNum - 3>= 0 && gearMeet[gearNum - 2].left != gearMeet[gearNum - 3].right) {
							rRotate(gear, gearMeet, gearNum - 3);
						}
					}
				}
				//오른쪽 보기
				if(gearNum + 1 < n && gearMeet[gearNum].right != gearMeet[gearNum + 1].left) {
					rRotate(gear, gearMeet, gearNum + 1);
					if(gearNum + 2 < n && gearMeet[gearNum + 1].right != gearMeet[gearNum + 2].left) {
						lRotate(gear, gearMeet, gearNum + 2);
						if(gearNum + 3 < n && gearMeet[gearNum + 2].right != gearMeet[gearNum + 3].left) {
							rRotate(gear, gearMeet, gearNum +3);
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
