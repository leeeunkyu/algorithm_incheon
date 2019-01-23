package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1759 {
	//��ȣ�� L���� ���ĺ� �ҹ���
	//�ּ� �Ѱ��� ������ �ΰ��� ���� ��������
	//c���� ���� ��ȣ����
	static char cryptoList[];
	static int cnt;	//�Ѱ��� ������ ����
	static int l;
	static int c;
	static char type[] = {'a', 'e', 'i', 'o', 'u'};
	static char constArray[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		l = Integer.parseInt(str[0]); //l ���� ��ȣ����
		c = Integer.parseInt(str[1]); //c ���� ������
		
		
		char temp[] = br.readLine().toCharArray();
		cryptoList = new char[c];
		
		for (int i = 0; i < c; i++) {
			if(temp[i] != ' ')
				cryptoList[i] = temp[i];
		}
		
		Arrays.sort(cryptoList);
		String tempStr = "";	//���� ����

		
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < 5; j++) {
					if(type[j] == cryptoList[i]) {
						tempStr += cryptoList[i];
					}
				}
			}
		constArray = tempStr.toCharArray(); //���� ����
		cnt = 0;
		System.out.println("??");
		System.out.println(dfs(0, ""));
		System.out.println("??");
	}
	//b c d e f i
	//b c d # f #
	//e i
	private static String dfs(int idx, String str) {
		if(str.length() == l) {
			for (int i = 0; i < constArray.length; i++) {
				for (int j = 0; j < l; j++) {
					if(constArray[i] == str.charAt(j)) {
						cnt ++;
					}
				}
			}
			if(cnt < 1 || l - cnt < 2) {
				str = str.substring(0, str.length()-1);				
			} else {
				return str;				
			}
		}
		for (int i = idx; i < cryptoList.length; i++) {
			System.out.println("!!");
			dfs(++idx, str + cryptoList[i]);
		}
		return str;
	}
}