package baekjoon;
import java.util.Scanner;

public class P_1966_print_queue {

		public static void main(String[] args) {

			Scanner scan = new Scanner(System.in);
			int t = scan.nextInt();
			for(int i=0; i<t; i++){
				int quesize = scan.nextInt();
				int searchidx = scan.nextInt();
				int startidx = 0;
				int[] que = new int[quesize];
				boolean[] checked = new boolean[quesize];
				for (int j = 0; j < quesize; j++) {
					que[j] = scan.nextInt();
				}
				int cnt = 1;
				while(true){
					int max = que[startidx];
					int nidx = startidx;
					int maxidx = startidx;
					for(int j=0; j< quesize; j++){
						if(que[nidx]>max&&checked[nidx]==false){
							max = que[nidx];
							maxidx = nidx;
						}
						if(++nidx==quesize) nidx=0;
					}
					checked[maxidx] = true;
					if(maxidx==searchidx){
						System.out.println(cnt);
						break;
					}else{
						cnt++;
						startidx = maxidx;
						for(int j=0; j< quesize; j++){
							if(++startidx==quesize) startidx=0;
							if(checked[startidx]==false){
								break;
							}
						}	
					}
				}
			}
		}
	}