package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_2382 {

	static int n; //셀 갯수
	static int m; //격리 시간
	static int k; //군집 갯수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			String[] str = br.readLine().split(" ");
			n = Integer.parseInt(str[0]);
			m = Integer.parseInt(str[1]);
			k = Integer.parseInt(str[2]);
			int[][] arrNum = new int[n][n]; //총 미생물 수
			Queue<VirusGrp>[][] arrDir = null;
			VirusGrp[] grpList = new VirusGrp[k];
			for (int i = 0; i < k; i++) {
				String virusInfo[] = br.readLine().split(" ");
				grpList[i] = new VirusGrp(
						Integer.parseInt(virusInfo[0]), //세로
						Integer.parseInt(virusInfo[1]), //가로
						Integer.parseInt(virusInfo[2]), //미생물 수
						Integer.parseInt(virusInfo[3]), //방향
						i, //군집번호
						false); //이동방향 
			}
			
			for (int i = 0; i < m; i++) {
				arrDir = new Queue[n][n]; //미생물 방향
				for (int i2 = 0; i2 < arrDir.length; i2++) {
					for (int j = 0; j < arrDir.length; j++) {
						arrDir[i2][j] = new LinkedList<VirusGrp>();
					}
				}
				goVirus(arrNum, arrDir, grpList);
				sumVirus(arrDir, grpList);
				update(arrDir, grpList);
			}
			
			int sum = 0;
			for (int i = 0; i < grpList.length; i++) {
				VirusGrp virusGrp = grpList[i];
				if(!virusGrp.isDie())
					sum += virusGrp.getVirusNum();
			}
			sb.append("#"+tc+" "+sum+" \n");
		}
		System.out.println(sb);
	}
	
	private static void update(Queue<VirusGrp>[][] arrDir, VirusGrp[] grpList) {
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Queue<VirusGrp> virusGrps = arrDir[i][j];
				VirusGrp virusGrp = virusGrps.poll();
				if(virusGrp.getVirusNum() != 0) {
					grpList[k] = virusGrp;
					k++;
				}
			}
		}
		
		/*for (int i = 0; i < grpList.length; i++) {
			System.out.println(grpList[i].toString());
		}*/
	}

	private static void goVirus(int[][] arrNum, Queue<VirusGrp>[][] arrDir, VirusGrp[] grpList) {
		int safeArr = n - 1;
		int[] dx = {0, 0, 0, -1, 1};
		int[] dy = {0, -1, 1, 0, 0};
		
		for (int i = 0; i < grpList.length; i++) {
			VirusGrp virusGrp = grpList[i];
			if(!virusGrp.isDie()) {
				int dir = virusGrp.getDir();
				int virusNum = virusGrp.getVirusNum();
				int x = virusGrp.getX() + dx[dir];
				int y = virusGrp.getY() + dy[dir];
				virusGrp.setX(x);
				virusGrp.setY(y);
				
				if(!(x >= 1 && x < safeArr && y >= 1 && y < safeArr)) {
					virusNum = virusNum / 2;
					if(virusNum == 0) {
						virusGrp.setDie(true);
						continue;						
					}
					virusGrp.setVirusNum(virusNum);
					if(dir % 2 == 0) {
						dir = dir - 1;
					} else {
						dir = dir + 1;
					}
					virusGrp.setDir(dir);
				}
				arrDir[y][x].add(virusGrp);
				virusGrp.setDie(true);
			}
		}
	}
	
	private static void sumVirus(Queue<VirusGrp>[][] arrDir, VirusGrp[] grpList) {
		int safeArr = n - 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Queue<VirusGrp> virusQueue = arrDir[i][j];
				int max = -1;
				int sum = 0;
				int virusDir = 1;
				while (!virusQueue.isEmpty()) {
					VirusGrp virusGrp = virusQueue.poll();
					int dir = virusGrp.getDir();
					int virusNum = virusGrp.getVirusNum();
					if(virusNum > max) {
						max = virusNum;
						virusDir = dir;
					}
					sum += virusNum;
				}
				virusQueue.add(new VirusGrp(i, j, sum, virusDir, 0, false));
				arrDir[i][j] = virusQueue;
			}
		}
	}
}

class VirusGrp {
	private int y;
	private int x;
	private int virusNum; //바이러스 갯수
	private int dir;//상, 하, 좌, 우  - 1, 2, 3, 4
	private int grpNum; //군집 번호
	private boolean isDie;
	
	public VirusGrp(int y, int x, int virusNum, int dir, int grpNum, boolean isDie) {
		super();
		this.y = y;
		this.x = x;
		this.virusNum = virusNum;
		this.dir = dir;
		this.grpNum = grpNum;
		this.isDie = isDie;
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
	public int getVirusNum() {
		return virusNum;
	}
	public void setVirusNum(int virusNum) {
		this.virusNum = virusNum;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public boolean isDie() {
		return isDie;
	}
	public int getGrpNum() {
		return grpNum;
	}
	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}
	public void setDie(boolean isDie) {
		this.isDie = isDie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VirusGrp [y=");
		builder.append(y);
		builder.append(", x=");
		builder.append(x);
		builder.append(", virusNum=");
		builder.append(virusNum);
		builder.append(", dir=");
		builder.append(dir);
		builder.append(", grpNum=");
		builder.append(grpNum);
		builder.append(", isDie=");
		builder.append(isDie);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
