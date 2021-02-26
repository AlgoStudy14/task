package imprepare.problems;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2798_블랙잭 {

	static int[] arr;
	static int[] cards;
	static int N,M,sum,ans;
	static boolean[] v;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N];
		cards = new int[3];
		v = new boolean[N];
		for (int i = 0; i <N; i++) {
			arr[i] = sc.nextInt();
		}
		nCr(0,0);
		System.out.println(ans);

	}
  //조합으로
	private static void nCr(int cnt, int start) {
    //카드 세개 골랐으면
		if(cnt==3) {
			sum = 0;
			for (int i = 0; i <3; i++) {
        //더해줌!
				sum+=cards[i];
			}
      //sum이 M 이하인 경우중 최댓값
			if(sum<=M) ans = Math.max(sum, ans);
			
			return;
		}
		for (int i = start; i <N; i++) {
			if(v[i]) continue;
			v[i] = true;
			cards[cnt] = arr[i];
			nCr(cnt+1,i+1);
			v[i] = false;
		}
		
	}

}
