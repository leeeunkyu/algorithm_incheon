package algorithm;

import java.util.LinkedList;
import java.util.Queue;

import javax.sound.midi.Synthesizer;

public class Combination_2 {
	
	static int[] arr;
	static int n;
	static Queue<int[]> q1;
	static Queue<int[]> q2;
	public static void main(String[] args) {
		arr = new int[30];
		for (int i = 0; i < 10; i++) {
			arr[i] = i;
		}
		double st;
		double et;
		n = arr.length;
		int r = 8;
		q1 = new LinkedList<int[]>();
		q2 = new LinkedList<int[]>();
		st = System.currentTimeMillis();
		for (int i = 0; i < (1 << n); i++) {
			if(Integer.bitCount(i) == r) {
				int[] temps = new int[r];
				int temp = 0;
				for (int j = 0; j < n; j++) {
					if(((1 << j) & i) > 0) {
						temps[temp] = j;
						int a = 10;
						int b = 5;
						int c = 0;
						for (int l = 0; l < a; l++) {
							for (int m = 0; m < b; m++) {
								++c;
							}
						}
					}
				}
				q1.add(temps);
			}
		}
		et = System.currentTimeMillis();
		System.out.println((double)(et - st));
		
		int[] combArr = new int[r];
		st = System.currentTimeMillis();
		doCombination(combArr, r, 0, 0);
		et = System.currentTimeMillis();
		System.out.println((double)(et - st));
		
		System.out.println(q1.size());
		System.out.println(q2.size());

	
	}
	private static void doCombination(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			int[] temps = new int[index];
			int a = 10;
			int b = 5;
			int c = 0;
			for (int i = 0; i < index; i++) {
				temps[i] = combArr[i];
				for (int j = 0; j < a; j++) {
					for (int j2 = 0; j2 < b; j2++) {
						++c;
					}
				}
			}
			q2.add(temps);
			//System.out.println();
		} else if(target == n) {
			return;
		} else {
			combArr[index] = target;
			doCombination(combArr, r - 1, index + 1, target + 1);
			doCombination(combArr, r, index, target + 1);
		}
	}
}
