package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = n / 5;
        
        switch (n % 5) {
        case 0:
            System.out.println(count);
            break;
        case 1:
        case 3:
            System.out.println(count + 1);
            break;
        case 2:
            if (count < 2) {
                System.out.println(-1);
            } else {
                System.out.println(count + 2);
            }
            break;
        case 4:
            if (count < 1) {
                System.out.println(-1);
            } else {
                System.out.println(count + 2);
            }
            break;
        }
		
	}
}
