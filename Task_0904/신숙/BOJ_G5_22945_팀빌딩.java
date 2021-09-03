import java.util.Scanner;

public class BOJ_G5_22945_팀빌딩 {
	/*
	 * <문제>
	 * 능력치가 다른 개발자 N명이 팀 빌딩을 위해 서있음.
	 * 하나의 팀은 2명이상의 개발자
	 * 팀 능력치 : 개발자 A와 B 사이에 존재하는 개발자 수 * Math.min(A능력치, B능력치)
	 * 팀 빌딩에서 나올 수 있는 팀 중 능력치 최대값.
	 * 
	 * <문제풀이>
	 * 두 포인터.
	 */
	static int N, ans;
	static int[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		list = new int[N + 1];
		for(int i = 0; i < N; i++)
			list[i] = sc.nextInt();
		
		int s = 0, e = N - 1;
		while(s < e) {
			int min = Math.min(list[s], list[e]);
			ans = Math.max(ans, (e - s - 1) * min);
			
			if(list[s] < list[e])
				s++;
			else
				e++;
		}
		System.out.println(ans);
	}
}
