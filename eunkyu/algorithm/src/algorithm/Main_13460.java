package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_13460 {
	final static int MAX_COUNT = 10;
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		int a = Integer.parseInt(str[0]); //����
		int b = Integer.parseInt(str[1]); //����
		char bord[][] = new char[a][b];
		RedBall rBall = new RedBall();
		BlueBall bBall = new BlueBall();
		Ball ball = new Ball();
		initSetting(br, bord, rBall, bBall, ball, a);
		int min = 999;
		int temp = 0;
		while(true) {		
			moveBallRight(bord, rBall, bBall, ball);
			if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
				temp = MAX_COUNT - count;
				System.out.println("r");
				break;
			}
			moveBallUp(bord, rBall, bBall, ball);
			if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
				temp = MAX_COUNT - count;
				System.out.println("u");
				break;
			}
			moveBallLeft(bord, rBall, bBall, ball);
			if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
				temp = MAX_COUNT - count;
				System.out.println("l");
				break;
			}
			moveBallDown(bord, rBall, bBall, ball);
			if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
				temp = MAX_COUNT - count;
				System.out.println("d");
				break;
			}
			if(count == MAX_COUNT) {
				System.out.println(-1);
				break;
			}
		}
		System.out.println(temp);
	}
	
	public static void initSetting(BufferedReader br, char bord[][], RedBall rBall, BlueBall bBall, Ball ball, int a) throws IOException {
		for (int i = 0; i < a; i++) {
			char set[] =  br.readLine().toCharArray();
			for (int j = 0; j < set.length; j++) {
				bord[i][j] = set[j];
				if(set[j] == 'O') {
					ball.setX(j);
					ball.setY(i);;
				}
				if(set[j] == 'R') {
					rBall.setX(j);
					rBall.setY(i);
				}
				if(set[j] == 'B') {
					bBall.setX(j);
					bBall.setY(i);
				}
				
			}
		}		
	}
	
	public static void moveBallRight(char bord[][], RedBall rBall, BlueBall bBall, Ball ball) {
		//���������� ������
		for (int i = 0; rBall.getX()+i < bord[rBall.getY()].length; i++) {
			System.out.println("r");
			if(bord[rBall.getY()][rBall.getX()+i] != '#') {
				rBall.setX(rBall.getX()+1);
				if(bBall.getX()+i < bord[rBall.getY()].length) {
					if(bord[bBall.getY()][bBall.getX()+i] != '#') {
						bBall.setX(bBall.getX()+1);
					}	
				}
				count++;
				if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
					break;
				}
			}else {
				break;
			}
		}
	}
	
	public static void moveBallLeft(char bord[][], RedBall rBall, BlueBall bBall, Ball ball) {
		//�������� ������
		for (int i = 0; 0 < rBall.getX()-i ; i++) {
			if(bord[rBall.getY()][rBall.getX()-i] != '#') {
				rBall.setX(rBall.getX()-1);
				if(0 < bBall.getX()-i) {
					if(bord[bBall.getY()][bBall.getX()-i] != '#') {
						bBall.setX(bBall.getX()-1);
					}
				}
				count++;
				if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
					break;
				}
			}else {
				break;
			}
		}
	}
	
	public static void moveBallUp(char bord[][], RedBall rBall, BlueBall bBall, Ball ball) {
		//���� ������
		for (int i = 0; 0 < rBall.getY()-i ; i++) {
			if(bord[rBall.getY()-i][rBall.getX()] != '#') {
				rBall.setY(rBall.getY()-1);
				if(0 < bBall.getY()-i) {
					if(bord[bBall.getY()-i][bBall.getX()] != '#') {
						bBall.setY(bBall.getY()-1);
					}
				}
				count++;
				if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
					break;
				}
			}else {
				break;
			}
		}
	}
	
	public static void moveBallDown(char bord[][], RedBall rBall, BlueBall bBall, Ball ball) {
		//�Ʒ��� ������
		for (int i = 0; rBall.getY()+i < bord.length; i++) {
			if(bord[rBall.getY()+i][rBall.getX()] != '#') {
				rBall.setY(rBall.getY()+1);
				if(bBall.getY()+i < bord.length) {
					if(bord[bBall.getY()+i][bBall.getX()] != '#') {
						bBall.setY(bBall.getY()+1);
					}
				}
				count++;
				if(bord[ball.getX()][ball.getY()] == bord[rBall.getX()][rBall.getY()]) {
					break;
				}
			}else {
				break;
			}
		}
	}
}

class RedBall {
	int x;
	int y;
	
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RedBall [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}

class BlueBall {
	int x;
	int y;
	
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlueBall [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}

class Ball {
	int x;
	int y;
	
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ball [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
}
