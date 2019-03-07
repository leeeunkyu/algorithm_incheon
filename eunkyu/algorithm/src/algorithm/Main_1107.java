package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1107 {
	final static int CURRENT_CH = 100;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean s = false;
		int n = Integer.parseInt(br.readLine()); //�̵��Ϸ��� ü��
		if(n == CURRENT_CH) {
			System.out.println(0);
			return;
		}
		m = Integer.parseInt(br.readLine()); //���峭 ��ư ����
		int[] brokenCh = new int[m];
		
		if(m <= 0) {
			s = true;
		}
		
		if(!s) {
			String[] brokenChInfo = br.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				brokenCh[i] = Integer.parseInt(brokenChInfo[i]);
			}
		}
		
		Arrays.sort(brokenCh);
		selectCh(n, brokenCh);
	}

	private static void selectCh(int n, int[] brokenCh) {
		int size = (""+n).length();
		int cpN = n;
		int[] chInfo = new int[size];
		int pivot = 0;
		int[] notBroken = new int[10 - m];
		//0 1 2 3 4 5 6 7 8 9
		int i2 = 0;
		int i3 = 0;
		for (int i = 0; i <= 9; i++) {
			if(i3 < m && brokenCh[i3] == i) {
				i3++;
			} else if(i2 < notBroken.length) {
				notBroken[i2] = i;
				i2++;
			}
		}
	
		if(i2 >= 1) {
			for (int i = size - 1; i >= 0; i--) {
				int temp = 1;
				for (int j = i; j >= 1; j--) {
					temp *= 10;
				}
				chInfo[pivot] = (n / temp) ;
				n %= temp;
				++pivot;
			}
			int[] caseList = new int[4];
			caseList[0] = aCaseSelect(cpN, size, chInfo, notBroken); //�ڸ����� �������
			caseList[1] = bCaseSelect(cpN, size, chInfo, notBroken); //Ŭ���
			caseList[2] = cCaseSelect(cpN, size, chInfo, notBroken); //�������
			caseList[3] = Math.abs(CURRENT_CH - cpN);
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 4; i++) {
				//System.out.println("i: "+i+" "+caseList[i]);
				if(min > Math.abs(caseList[i])) {
					min = Math.abs(caseList[i]);
				}
			}
			System.out.println(min);
		} else {
			//����ִ� ��ȣ��ư�� ���°��
			System.out.println(Math.abs(n - CURRENT_CH));
			return;
		}
	}

	//�ڸ����� �������
	private static int cCaseSelect(int n, int size, int[] chInfo, int[] notBroken) {
		if(size <= 1)
			return Integer.MAX_VALUE;
		String num = "";
		for (int i = 0; i < size - 1; i++) {
			num += notBroken[notBroken.length - 1];
		}
		return (size - 1) + Math.abs(n - Integer.parseInt(num));
	}

	//�ڸ����� Ŭ ���
	private static int bCaseSelect(int n, int size, int[] chInfo, int[] notBroken) {
		int pivot = 0;
		String num = "";
		if(notBroken[0] == 0) {
			if(notBroken.length == 1) {
				return n - CURRENT_CH < n ? n - CURRENT_CH : n;
			}
			++pivot;
			num += notBroken[1];
		}
		
		for (int i = pivot; i < size + 1; i++) {
			num += notBroken[0];
		}
		if(num.length() < 1)
			num += Integer.MAX_VALUE;
		return (size + 1) + Math.abs(n - Integer.parseInt(num));
	}

	//�ڸ����� �������
	private static int aCaseSelect(int n, int size, int[] chInfo, int[] notBroken) {
		String num = "";
		int top = chInfo[0];
		int topIndex = 0;
		int topTemp = Integer.MAX_VALUE;
		boolean check = false;
		for (int i = 0; i < notBroken.length; i++) {
			if(top == notBroken[i]) {
				topIndex = i;
				check = true;
				break;
			} else {
				int sub = Math.abs(top - notBroken[i]);
				if(sub < topTemp) {
					topTemp = sub;
					topIndex = i;
				}
			}
		}
		
		
		loop:
		for (int i = 0; i < size; i++) {
			int val = chInfo[i];
			int temp = Integer.MAX_VALUE;
			for (int j = 0; j < notBroken.length; j++) {
				if(val == notBroken[j]) {
					num += val;
					continue loop;
				} else {
					int sub = Math.abs(val - notBroken[j]);
					if(sub < temp) {
						temp = sub;
						chInfo[i] = notBroken[j];
					}
				}
			}
			num += chInfo[i];
		}
		
		String numA = "";
		String numB = "";
		for (int i = 0; i < size; i++) {
			if(!check) {
				//ù ���� notbroken�� ����.
				if(notBroken[topIndex] > top) {
					if(i == 0) {
						numA += notBroken[topIndex];
						if(topIndex - 1 >= 0)
							numB += notBroken[topIndex - 1];
					} else {
						numA += notBroken[0];
						if(topIndex - 1 >= 0)
							numB += notBroken[notBroken.length - 1];
					}
				} else if(notBroken[topIndex] < top) {
					if(i == 0) {
						numA += notBroken[topIndex];
						if(topIndex + 1 < notBroken.length) {
							numB += notBroken[topIndex + 1];
						}
					} else {
						numA += notBroken[notBroken.length - 1];
						if(topIndex + 1 < notBroken.length) {
							numB += notBroken[0];
						}
					}
				}	
			} else {
				if(i == 0) {
					if(topIndex + 1 < notBroken.length)
						numA += notBroken[topIndex + 1];
					if(topIndex - 1 >= 0)
						numB += notBroken[topIndex - 1];
				} else {
					if(topIndex + 1 < notBroken.length)
						numA += notBroken[0];
					if(topIndex - 1 >= 0)
						numB += notBroken[notBroken.length - 1];
				}
			}
		}
/*		System.out.println(num+" "+numA+" "+numB);
*/		if(numA.length() == 0) 
			numA += Integer.MAX_VALUE;
		if(numB.length() == 0) 
			numB += Integer.MAX_VALUE;
		if(num.length() == 0)
			num += Integer.MAX_VALUE;
		int a = Math.abs(n - Integer.parseInt(num));
		int b = Math.abs(n - Integer.parseInt(numA));
		int c = Math.abs(n - Integer.parseInt(numB));
		System.out.println(num+" "+numA+" "+numB);
		int min;
		if(a < b) {
			if(a < c) {
				min = a;
			} else {
				min = c;
			}
		} else {
			if(b < c) {
				min = b;
			} else {
				min = c;
			}
		}
/*		System.out.println(min);
*/		return size + min;
	}
}
