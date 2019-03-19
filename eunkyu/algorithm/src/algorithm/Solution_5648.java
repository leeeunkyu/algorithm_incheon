package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.omg.CORBA.portable.ValueInputStream;
public class Solution_5648 {
	
	static int n;
	static Bio_5648[] bioList;
	static int MAX_SIZE_X;
	static int MAX_SIZE_Y;
	static int size;
	static int sum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//원자들의 이동 방향은 상(0), 하(1), 좌(2), 우(3)로 주어진다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			size = n;
			bioList = new Bio_5648[n];
			int maxX = Integer.MIN_VALUE;
			int minX = Integer.MAX_VALUE;
			int maxY = Integer.MIN_VALUE;
			int minY = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				String[] str = br.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int dir = Integer.parseInt(str[2]);
				int k = Integer.parseInt(str[3]);
				Bio_5648 bio = new Bio_5648(y, x, dir, k, false);
				if(x > maxX) {
					maxX = x;
				}
				if(x < minX) {
					minX = x;
				}
				if(y > maxY) {
					maxY = y;
				}
				if(y < minY) {
					minY = y;
				}
				bioList[i] = bio;
			}
			
			for (int i = 0; i < n; i++) {
				Bio_5648 bio = bioList[i];
				bio.setX(bio.getX() + (minX*-1));
				bio.setY(bio.getY() + (minY*-1));
			}
			MAX_SIZE_X = maxX + (minX * -1);
			MAX_SIZE_Y = maxY + (minY * -1);
			sum = 0;
			goBio();				
			sb.append("#"+tc+" "+sum+"\n");
		}
		System.out.println(sb);
	}
	private static void goBio() {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		int[][] visited = new int[MAX_SIZE_X + 1][MAX_SIZE_Y + 1];
		while(size > 1) {
			HashSet<Integer> deleteList = new HashSet<Integer>();
			for (int i = 0; i < size; i++) {
				
				Bio_5648 bio = bioList[i];
				int x = bio.getX();
				int y = bio.getY();
				int dir = bio.getDir();
				x += dx[dir];
				y += dy[dir];
								
				if(x >= 0 && x < MAX_SIZE_X + 1 && y >= 0 && y < MAX_SIZE_Y + 1) {
					if(visited[y][x] == 0) {
						visited[y][x] = i;	
					} else {
						deleteList.add(i);
						deleteList.add(visited[y][x]);
					}	
				} else {
					bioList[i].setOut(true);
					deleteList.add(i);
				}
				
			}

			for (int idx: deleteList) {
				Bio_5648 bio = bioList[idx];
				int x = bio.getX();
				int y = bio.getY();
				boolean isOut = bio.isOut;
				if(!isOut) {
					sum += bioList[idx].getK();
					visited[y][x] = 0;
				}
				bioList[idx] = bioList[size - 1];
				--size;
			}
			
			for (int i = 0; i < size; i++) {
				Bio_5648 bio = bioList[i];
				int x = bio.getX();
				int y = bio.getY();
				int dir = bio.getDir();
				x += dx[dir];
				y += dy[dir];
				Bio_5648 tempbio = new Bio_5648(y, x, dir, bio.getK(), false);
				bioList[i] = tempbio;
			}			
		}
	}
}

class Bio_5648 {
	
	int y;
	int x;
	int dir;
	int k;
	boolean isOut;
	public Bio_5648(int y, int x, int dir, int k, boolean isOut) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
		this.k = k;
		this.isOut = isOut;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	
}
