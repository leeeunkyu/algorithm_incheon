package algorithm;

public class Zoom_1 {
	public static void main(String[] args) {
		int[] A = {2, 3, 4, 6, 6, 7, 8};
		new Zoom_1().solution(A);
	}

	private void solution(int[] a) {
		int cnt = 0;
		for (int i = 0; i < a.length; i++) {
			boolean res = checkSort(a, i);	
			if(res) {
				++cnt;
			}
		}
		System.out.println(cnt);
	}

	private boolean checkSort(int[] a, int idx) {
		int val = a[0];
		for (int i = 0; i < a.length; i++) {
			if(i == idx)
				continue;
			if(val > a[i]) {
				return false;
			}
			val = a[i];
		}
		return true;
	}
}
