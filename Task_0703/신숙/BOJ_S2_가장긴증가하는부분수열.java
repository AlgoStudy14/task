import java.io.IOException;
import java.util.Scanner;

public class BOJ_S2_가장긴증가하는부분수열 {
	/*
	 * <문제>
	 * 말 그대로 가장 길게 증가할 수 있는 부분수열의 길이 즉, 개수를 구하는 문제.(LIS?)
	 * 
	 * <풀이>
	 * 1. 값 입력받기.
	 * 2. dp함수 돌려서 dp[n]에 0부터 n까지 올수있는 가장 긴 길이를 넣어서 결과는 반복문에서 최대값 반환.
	 * 3. 예전 전신주?문제처럼 뒤에서부터 앞으로 가면 더 보기 편할듯.
	 */
	static int N, ans;
	static int[] map, dp;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N];
		dp = new int[N];
		
		for(int i = 0; i < N; i++)
			map[i] = sc.nextInt();
		
		for(int i = 0; i < N; i++)
			dpfunc(i);
		
		for(int i = 0; i < N; i++)
			ans = Math.max(ans, dp[i]);
		
		System.out.println(ans);
	}
	
	static int dpfunc(int i ) {
		if(dp[i] == 0) {
			dp[i] = 1;
			for(int a = i - 1; a >= 0; a--) {
				if(map[a] < map[i])
					dp[i] = Math.max(dp[i], dpfunc(a) + 1);
			}
		}
		return dp[i];
	}
}
