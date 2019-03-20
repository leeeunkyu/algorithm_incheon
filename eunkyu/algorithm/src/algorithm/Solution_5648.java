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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			Bio_5648[] bios = new Bio_5648[n];
			for (int i = 0; i < n; i++) {
				String[] info = br.readLine().split(" ");
				int x = Integer.parseInt(info[0]);
				int y = Integer.parseInt(info[1]);
				int dir = Integer.parseInt(info[2]);
				int k = Integer.parseInt(info[3]);
				
				bios[i] = new Bio_5648(y, x, k, dir);
			}	
			int res = goBio(bios);
			sb.append("#"+tc+" "+res+"\n");
		}
		
		System.out.println(sb);
	}
	private static int goBio(Bio_5648[] bios) {
		int res = 0;
		PriorityQueue<Dist_5648> minDists = new PriorityQueue<Dist_5648>();
		for (int i = 0; i < n; i++) {
			Bio_5648 bio = bios[i];
			int x1 = bio.getX();
			int y1 = bio.getY();
			int k1 = bio.getK();
			int dir1 = bio.getDir();
			
			double dist = -1;
			boolean isBoom = false;
			Queue<Integer> boomBio = new LinkedList<Integer>();
			
			for (int j = i + 1; j < n; j++) {
				Bio_5648 bio2 = bios[j];
				dist = -1;

				int x2 = bio2.getX();
				int y2 = bio2.getY();
				int k2 = bio2.getK();
				int dir2 = bio2.getDir();
	
				if(dir1 == dir2 || k2 == -1 || k1 == -1)
					continue;
				
				if(!((dir1 == 1 && dir2 == 2) || (dir1 == 2 && dir2 == 1)) 
						&& Math.abs(dir1 - dir2) == 1) {
					if((dir1 == 1 || dir1 == 0) && x1 == x2) {
						if(dir1 == 1 && y1 > y2)
							dist = (double)Math.abs(y2 - y1) / 2;
						if(dir1 == 0 && y1 < y2)
							dist = (double)Math.abs(y2 - y1) / 2;
					}
					if((dir1 == 2 || dir1 == 3) && y1 == y2) {
						if(dir1 == 2 && x1 > x2)
							dist = (double)Math.abs(x1 - x2) / 2;
						if(dir1 == 3 && x1 < x2)
							dist = (double)Math.abs(x1 - x2) / 2;
					}
				} else {
					dist = selectDist(x1, y1, dir1, x2, y2, dir2);
/*					System.out.println(dist);
*/				}
				
				if(dist != -1) {
					boomBio.add(j);
					isBoom = true;
				}
			}
			
			if(isBoom) {
				while(!boomBio.isEmpty()) {
					int val = boomBio.poll();
					//res += bios[val].getK();
					double data = Math.abs(x1 - bios[val].getX()) + Math.abs(y1 - bios[val].getY()) / 2;
					minDists.add(new Dist_5648(i, val, data));
				}
			}
			
		}
		boolean visited[] = new boolean[n];
		
		double preDist = -1;
		double preHead = -1;
		while(!minDists.isEmpty()) { 
			Dist_5648 dist = minDists.poll();
/*			System.out.println(dist.getA()+"  "+dist.getB());
*/			int a = dist.getA();
			int b = dist.getB();
			double d = dist.getDist();
			if(preDist != d && !visited[a] && !visited[b]) {
				visited[a] = true;
				visited[b] = true;
				preDist = d;
				preHead = a;
				continue;
			}
			if(preDist == d) {
				visited[a] = true;
				visited[b] = true;
				preDist = d;
			}
			
		}
		res = 0;
		for (int k = 0; k < visited.length; k++) {
			if(visited[k])
				res += bios[k].getK();
		}		
		return res;
		
	}
	private static int selectDist(int x1, int y1, int dir1, int x2, int y2, int dir2) {
		if((dir1 == 0 || dir1 == 1) && dir2 == 2 && x1 < x2) {
			if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
				return Math.abs(x1 - x2);
		}
		if((dir1 == 0 || dir1 == 1) && dir2== 3 && x1 > x2) {
			if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
				return Math.abs(x1 - x2);
		}
		if((dir1 == 2 || dir1 == 3) && dir2 == 0 && y1 > y2) {
			if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
				return Math.abs(y1 - y2);
		}
		if((dir1 == 2 || dir1 == 3) && dir2== 1 && y1 < y2) {
			if(Math.abs(x1 - x2) == Math.abs(y1 - y2))
				return Math.abs(y1 - y2);
		}
		return -1;
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
		if(this.getDist() - dist.getDist() < 0)
			return -1;
		if(this.getDist() - dist.getDist() > 0)
			return 1;
		return 0;
	}
}