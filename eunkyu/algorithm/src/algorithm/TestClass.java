package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {
	
	static String[] A;
	static int n;
	public static void main(String[] args) throws IOException {
		A = new String[5];
		A[0] = "A";
		A[1] = "B";
		A[2] = "C";
		A[3] = "D";
		A[4] = "E";
		
		n = A.length;
		int r = 3;
		int[] combArr = new int[r];
		doCombination(combArr, r, 0, 0);
	}
	private static void doCombination(int[] combArr, int r, int index, int target) {
		if(r == 0) {
			//더 이상 뽑을게 x 검사 시작
			for (int i = 0; i < index; i++) {
				System.out.print(combArr[i]+" ");
			}
			System.out.println();
		}else if(target == n) {
			//끝까지 다 돌았다
			return;
		}else {
			combArr[index] = target;
			doCombination(combArr, r - 1, index + 1, target + 1); //지금 단계 경우의수 선택
			doCombination(combArr, r, index, target + 1); //선택 안함
		}
		
	}
}