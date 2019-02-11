package swexpertacademy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P_5658_Treasurebox_password {
	static int one_size;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = Integer.parseInt(scan.nextLine());
		PriorityQueue<String> prique = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				for (int i = 0; i < one_size; i++) {
					if(str1.charAt(i)==str2.charAt(i))continue;
					if(str1.charAt(i)>str2.charAt(i)) return -1;
					return 1;
				}
				return 0;
			}
		});
		for (int test = 1; test < t+1; test++) {
			String[] n_k = scan.nextLine().split(" ");
			int n = Integer.parseInt(n_k[0]);
			int k = Integer.parseInt(n_k[1]);
			one_size = n/4;
			String password = scan.nextLine();
			prique.clear();
			for (int i = 0; i < one_size; i++) {
				for (int j = 0; j < 3; j++) {
					String substr = password.substring(j*one_size+i, (j+1)*one_size+i);
					if(prique.contains(substr))continue;
					prique.add(substr);
				}
				String substr2 = password.substring(password.length()-one_size+i)+password.substring(0,i);
				if(prique.contains(substr2))continue;
				prique.add(substr2);
			}
			for (int i = 0; i < k-1; i++) {
				prique.poll();
			}
			String result = prique.poll();
			System.out.println("#"+test+" "+Integer.parseInt(result, 16));
		}
		
	}

}
