package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//4:30
public class Solution_5648 {

	static int n;	//원자들의 수
	static Bio_5648[] bios;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			bios = new Bio_5648[n];
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				int x = Integer.parseInt(info[0]);
				int y = Integer.parseInt(info[1]);
				int dir = Integer.parseInt(info[2]);
				int k = Integer.parseInt(info[3]);
				
				bios[i] = new Bio_5648(y, x, k, dir);
			}	
			int res = goBio();
			sb.append("#"+tc+" "+res+"\n");
		}
		
		System.out.println(sb);
	}
	//원자들의 이동 방향은 상(0), 하(1), 좌(2), 우(3)로 주어진다.
	private static int goBio() {
		PriorityQueue<Dist_5648> minDists = new PriorityQueue<Dist_5648>();
		for (int i = 0; i < n; i++) {
			Bio_5648 bio = bios[i];
			int x1 = bio.getX();
			int y1 = bio.getY();
			int dir1 = bio.getDir();
			
			double dist = -1;
			double dist2 = -1;
			
			for (int j = 0; j < n; j++) {
				if(i == j)
					continue;

				Bio_5648 bio2 = bios[j];
				int x2 = bio2.getX();
				int y2 = bio2.getY();
				int dir2 = bio2.getDir();
				if(dir1 == dir2)
					continue;
				
				dist = -1;
				dist2 = -1;
				
				if(Math.abs(dir1 - dir2) == 1) {
					if(!(dir1 == 1 && dir2 == 2) || (dir1 == 2 && dir2 == 1)) {
						dist = selectDist2(x1, y1, dir1, x2, y2, dir2);
					}
				}
				dist2 = selectDist(x1, y1, dir1, x2, y2, dir2);

				
				if(dist != -1 || dist2 != -1) {
					double data = 0;
					if(dist == -1) {
						data = dist2;
					} else {
						data = dist;
					}
					minDists.add(new Dist_5648(i, j, data));
				}
			}			
		}
		return goAns(minDists);		
	}
	
	private static int goAns(PriorityQueue<Dist_5648> minDists) {
		double visited[] = new double[n];
		int res = 0;		

		while(!minDists.isEmpty()) { 
			Dist_5648 dist = minDists.poll();
			int a = dist.getA();
			int b = dist.getB();
			double d = dist.getDist();
			if(bios[a].getK() == 0 || bios[b].getK() == 0)
				continue;
			if(visited[a] == 0 && visited[b] == 0) {
				visited[a] = dist.getDist();
				visited[b] = dist.getDist();
				res += (bios[a].getK() + bios[b].getK());
			}
			if(visited[a] == 0 && visited[b] != 0) {
				if(visited[b] == d) {
					visited[a] = d;
					res += (bios[a].getK());

				}
			}
			if(visited[a] != 0 && visited[b] == 0) {
				if(visited[a] == d) {
					visited[b] = d;
					res += (bios[b].getK());
				}
			}
		}			
		return res;
	}
	
	private static double selectDist2(int x1, int y1, int dir1, int x2, int y2, int dir2) {
		double dist = -1;
		if((dir1 == 1 || dir1 == 0) && x1 == x2) {
			if(dir1 == 1 && dir2 == 0 && y1 > y2)
				dist = (double)Math.abs(y2 - y1) / 2;
			if(dir1 == 0 && dir2 == 1 && y1 < y2)
				dist = (double)Math.abs(y2 - y1) / 2;
		}
		if((dir1 == 2 || dir1 == 3) && y1 == y2) {
			if(dir1 == 2 && dir2 == 3 && x1 > x2)
				dist = (double)Math.abs(x1 - x2) / 2;
			if(dir1 == 3 && dir2== 2 && x1 < x2)
				dist = (double)Math.abs(x1 - x2) / 2;
		}
		return dist;
	}
	
	private static double selectDist(int x1, int y1, int dir1, int x2, int y2, int dir2) {
		double dist = -1;
		boolean c = false;
		if((dir1 == 0 || dir1 == 1) && dir2 == 2 && x1 < x2) {
			if((dir1 == 1 && y1 > y2) || (dir1 == 0 && y1 < y2))
				c = true;
		}
		if((dir1 == 0 || dir1 == 1) && dir2== 3 && x1 > x2) {
			if((dir1 == 1 && y1 > y2) || (dir1 == 0 && y1 < y2))
				c = true;
		}
		if((dir1 == 2 || dir1 == 3) && dir2 == 0 && y1 > y2) {
			if((dir1 == 2 && x1 > x2) || (dir1 == 3 && x1 < x2))
				c = true;
		}
		if((dir1 == 2 || dir1 == 3) && dir2== 1 && y1 < y2) {
			if((dir1 == 2 && x1 > x2) || (dir1 == 3 && x1 < x2))
				c = true;
		}
		if(c) {
			if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
				dist =  Math.abs(x1 - x2);
		}
		return dist;
	}
}

class Bio_5648 {
	int y;
	int x;
	int k;
	int dir;
	
	public Bio_5648(int y, int x, int k, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.k = k;
		this.dir = dir;
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
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
}

class Dist_5648 implements Comparable<Dist_5648>{
	int a;
	int b;
	double dist;
	
	public Dist_5648(int a, int b, double dist) {
		super();
		this.a = a;
		this.b = b;
		this.dist = dist;
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}

	@Override
	public int compareTo(Dist_5648 dist) {
        return dist.dist > this.dist ? -1 : 1;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dist_5648 [a=");
		builder.append(a);
		builder.append(", b=");
		builder.append(b);
		builder.append(", dist=");
		builder.append(dist);
		builder.append("]");
		return builder.toString();
	}
	
}