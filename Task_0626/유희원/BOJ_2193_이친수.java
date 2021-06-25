package algo0626;

import java.util.Scanner;

public class BOJ_2193_이친수 {

	static long[] checked;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		checked = new long[N+1];
		
		System.out.println(dp(N));

	}
	private static long dp(int n) {
		if(n==1) return 1;
		else if(n==2) return 1;
		else if(checked[n]!= 0) return checked[n];
		else {
			return checked[n] = dp(n-1)+dp(n-2);
		}
	}

}
