package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class TestClass {
    static int n,m,k;
    static int[][] arr,copy;
    static List<ArrayList<ArrayList<Pair>>> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // n*n �迭
        m = sc.nextInt(); // ���� ���� ��
        k = sc.nextInt(); // K���� ������.
        
        arr = new int[n][n];
        copy = new int[n][n]; // �߰� �Ǿ���� ���
        // n���� arrayList ����
        for(int i=0;i<n;i++) {
            list.add(new ArrayList<>());
            for(int j=0;j<n;j++)
                list.get(i).add(new ArrayList<>());
        }
        // �ʱ� ��а�
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                copy[i][j] = sc.nextInt();
                arr[i][j] = 5; // �ʱ� ����� 5
            }
        }
        // m���� �ٿ� �󵵰� ���� ���������� (x,y), ��������
        for(int i=0;i<m;i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int age = sc.nextInt();
            list.get(x-1).get(y-1).add(new Pair(x-1, y-1, 1, age));
        }
        
        // K�� ����
        for(int i=0;i<k;i++) {
            // 1���� 4����
            solveSpring();
            solveSummer();
            solveFall();
            solveWinter();
        }
        // k���� �帥 �� ����ִ� ���� ����
        int result = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                result += list.get(i).get(j).size();
            }
        }
        System.out.println(result);
    }
    // �ܿ￡�� ���� ����� �߰��Ѵ�. �� ĭ�� �߰��Ǵ� ����� �ʱ� �Է� ����̴�.
    private static void solveWinter() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j] += copy[i][j];
            }
        }
    }
    // �������� ������ �����Ѵ�. ���̰� 5�� ����� ������
    // ���� 8ĭ�� ���̰� 1�� ������ �����.
    static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    private static void solveFall() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    for(int k=0;k<list.get(i).get(j).size();k++) {
                        // age�� 5�� ����� �ƴ϶�� �н�
                        if(list.get(i).get(j).get(k).age%5!=0) continue;
                        for(int z=0;z<8;z++) {
                            int tx = i+dir[z][0];
                            int ty = j+dir[z][1];
                            if(tx<0 || ty<0 || tx>=n || ty>=n) continue;
                            // �ش� ĭ�� ���̰� 1�� ���� ����
                            list.get(tx).get(ty).add(0,new Pair(tx, ty, 1, 1));
                        }
                    }
            }
        }
    }
    // �������� ���� ���� ������ ������� ���Ѵ�.
    // ���� ������ ����/2�� ������� �ش�ĭ�� �߰��ȴ�.
    private static void solveSummer() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                    // �ش� ��ǥ�� �������� ���鼭 0�ΰ��� ��������.
                    for(int k=0;k<list.get(i).get(j).size();k++) {
                        // ���� �������
                        if(list.get(i).get(j).get(k).v==0) {
                            arr[i][j] += list.get(i).get(j).get(k).age/2;
                            // k--�� ���־� ������ �ɷ� ���� ������ ���ش�.
                            list.get(i).get(j).remove(k);
                            k--;
                        }
                }
            }
        }
    }
    
    // ������ ������ ���̸� ŭ ����� �԰� ���̰� +1 (�ڽ�ĭ�� ����)
    // �ϳ��� ĭ�� ���� ���� ������ �ֵ��� ���� � ������ ���� �Դ´�.
    // ����� ������ �������ϸ� ���� �ʰ� �ٷ� ���
    private static void solveSpring() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                // ���� �˻�
                for(int k=0;k<list.get(i).get(j).size();k++) {
                    if(list.get(i).get(j).get(k).age>arr[i][j]) {
                        // ������ ���̰� �� ũ�ٸ� ���
                        list.get(i).get(j).get(k).v = 0;
                    }else {
                        // �ش����� ���� ��ŭ ��� ����
                        arr[i][j] -= list.get(i).get(j).get(k).age++;
                    }
                }
            }
        }
    }
    
    static class Pair{
        int x,y; // ��ġ
        int v; // ���� (1����,0����)
        int age; // ����
        Pair(int x,int y,int v,int age){
            this.x = x;
            this.y = y;
            this.v = v;
            this.age = age;
        }
    }
}