import java.util.Scanner;

public class BOJ_S3_계단오르기 {
	/*
	 * <문제>
	 * 계단 시작점부터 꼭대기에 위치한 곳까지 계단을 밟으면 계단에 있는 점수를 얻음.
	 * 1. 한번에 한계단 혹은 두계단씩 오르기 가능.
	 * 2. 연속으로 세개의 계단을 밟아선 안됨.
	 * 3. 마지막 계단은 반드시 밟아야함.
	 * 
	 * <문제풀이>
	 * 계단의 개수 <= 300, 점수 <= 10,000
	 * 한번에 한 계단 올라가는 경우(그 다음 건너뛰는 경우)와 두개 밟는 경우를 나누어서 최대값인 경우를 DP에 저장.
	 */
	static int N;
	static int[] stair, dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		stair = new int[N + 1];
		dp = new int[N + 1];
		
		for(int i = 0; i < N; i++)
			stair[i] = sc.nextInt();
		
		dp[0] = 0;
		dp[1] = stair[1];
		
		if(N >= 2)
			dp[2] = stair[1] + stair[2];
		
		for(int i = 3; i <= N; i++)
			//		     바로전칸을 뛰어넘었을때, 두칸 전에서 뛰어넘어서 전칸과 이번 칸을 밟을 때
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
		
		System.out.println(dp[N]);
	}
}
