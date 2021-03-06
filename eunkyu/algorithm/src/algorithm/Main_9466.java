package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1 2 3 4 5 6 7
 * 0 1 0 0 0 0 0 1
 * 0 0 0 0 0 0 0 2
 * 1 0 1 0 1 0 0 3 
 * 0 0 0 0 0 1 0 4
 * 0 0 0 0 0 0 0 5
 * 0 0 0 0 0 0 1 6
 * 0 0 0 1 0 0 0 7
 */
public class Main_9466 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		int studentNum = 0;
		int arr[][] = null;
		for (int i = 0; i < testCase; i++) {
			studentNum = Integer.parseInt(br.readLine());
			int pickList[] = new int[studentNum];
			arr = new int[studentNum][studentNum];
			char temp[] = br.readLine().toCharArray();
			for (int j = 0; j < studentNum; j++) {
				if(temp[j] == ' ')
					continue;
				int a = temp[j] - '0';
				System.out.println("a: "+a);
				for (int k = 0; k < arr.length; k++) {
					for (int l = 0; l < arr.length; l++) {
						System.out.print(arr[i][j]+" ");
					}
					System.out.println();
				}
				
				arr[a-1][j] = 1;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		
		
		
	}
}
