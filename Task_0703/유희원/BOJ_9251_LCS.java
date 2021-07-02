import java.util.Arrays;
import java.util.Scanner;

public class BOJ_6603_로또 {


	static int k,R;
	static int[] num, target;
	static int tot;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			k=sc.nextInt();
			if(k==0) break;
			R=6;
			target = new int[k];
			num = new int[R];
			for (int i = 0; i < k; i++) {
				target[i] = sc.nextInt();
			}
			tot =0;
			nCr(0,0);
			System.out.println();
		}
		
	}

	private static void nCr(int start, int cnt) {
		if(cnt==R) { //R개를 얻으면
			tot++;
			//System.out.println(Arrays.toString(num));
			for (int i = 0; i < 6; i++) {
				System.out.print(num[i]+" ");
			}
			System.out.println();
			return; //매우 중요
			
		}
		for (int i = start; i < k; i++) {
			num[cnt]=target[i]; //또는i+1
			nCr(i+1, cnt+1); //start가 아닌 i 인것에 주의!!!!
		}
		
		
	}

}
