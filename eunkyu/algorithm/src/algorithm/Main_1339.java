package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_1339 {
	
	static int n;
	static String[] words;
	static HashSet<Character> hs;
	static double[] map;
	static int[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		words = new String[n];
		visited = new int[26];
		
		hs = new HashSet<Character>();
		
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
		}
		map = new double[26];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				map[words[i].charAt(j) - 'A'] += Math.pow(10, words[i].length() - j);
				hs.add(words[i].charAt(j));
			}
		}
		
		
		int num = 9;
		double max = Integer.MIN_VALUE;
		int maxIndex = 0;
		
		for (int idx = 0; idx < hs.size(); idx++) {
			max = Integer.MIN_VALUE;
			for (int i = 0; i < 26; i++) {
				double val = map[i];
				if(val > max) {
					max = val;
					maxIndex = i;
				}
			}
			visited[maxIndex] = num;
			--num;
			map[maxIndex] = 0;	
		}
		
		/*for (int i = 0; i < 26; i++) {
			System.out.print(visited[i]+" ");
		}*/
		int res = 0;
		for (int i = 0; i < n; i++) {
			String str = "";
			for (int j = 0; j < words[i].length(); j++) {
				char val = words[i].charAt(j);
				str += visited[val - 'A'];
			}
			res += Integer.parseInt(str);
			str = "";
		}
		System.out.println(res);
		
	}
}
