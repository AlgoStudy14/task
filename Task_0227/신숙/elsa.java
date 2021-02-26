package Jungol;

import java.util.Scanner;

public class elsa {
	static int T, N, count;
	static int[] bulb, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			bulb = new int[N + 1];
			ans = new int[N + 1];
			count = 0;
			for(int i = 1; i <= N; i++) {
				ans[i] = sc.nextInt();
			}
			
			for(int i = 1; i <= N; i++) {
				if(bulb[i] == ans[i])
					continue;
				else {
					count++;
					for(int j = i; j <= N; j += i) {
						if(bulb[j] == 0)
							bulb[j] = 1;
						else
							bulb[j] = 0;
					}
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}
}
