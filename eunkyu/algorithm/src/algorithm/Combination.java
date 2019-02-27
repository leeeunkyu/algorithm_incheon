package algorithm;

import java.util.Arrays;

public class Combination {
	public static void main(String[] ar){
        Combination ex = new Combination();
        int[] arr = { 1, 2, 3 };
        boolean[] isMember = new boolean[3];
        int n = arr.length;
        int r = 2;
        int[] combArr = new int[n];
 
        ex.doCombination(combArr, n, r, 0, 0, isMember);
    }
 
    public void doCombination(int[] combArr, int n, int r, int index, int target, boolean[] isMember){       
    	 System.out.println("=> "+n+" "+r+" "+index+" "+target);
         
         // r ==0 �̶� ���� ���� ���Ҹ� �� �̾Ҵٴ� ��.
         if(r == 0){
             System.out.println(Arrays.toString(combArr));
             for(int i=0; i<index; i++)System.out.print(isMember[combArr[i]] + " ");
            
             System.out.println();
          
         //������ �� �˻��� ���..1���� ���� ���¿��� ������ ����� ������ return ���� ����
         }else if(target == n){ 
              
             return;
          
         }else{
             combArr[index] = target;
             // (i) �̴� ��� 
             // �����ϱ�, r-1
             // �̾����� ���� index + 1 ����� ��
             doCombination(combArr, n, r-1, index+1, target+1, isMember);
              
             //(ii) �� �̴°��
             // �Ȼ����ϱ� �״�� r
             // �Ȼ̾�����, index�� �״��!
             // index�� �״���ϸ�, ���� ��� ���� 1�� ������ comArr�� �־�� ���� ��Ϳ� �ٽ� �����ļ� �ᱹ 1������ ���� �ʰ� �ȴ�.
             doCombination(combArr, n, r, index, target+1, isMember); 
         }
    }

}
