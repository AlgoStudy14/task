import java.util.Scanner;

public class BOJ_G1_로봇조종하기 {
	/*
	 * <문제>
	 * N * M 지형. 로봇은 지형에서 위쪽으로는 움직이기 힘들다. 이미 탐사한지역은 탐사하지 않는다.
	 * (1, 1)에서 (N, M)으로 보낸다. 위 조건을 만족하며 탐사한지역의 가치가 최대가치는?
	 * 
	 * <문제풀이>
	 * dp..
	 * 
	 * 
	 */
	static int N, M;
	static int[][] map, dp, temp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		dp = new int[N][M];
		temp = new int[2][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();
		}
		//제일 윗라인 0번째줄 초기값 넣어주기. 우측으로 가는 방법 밖에 없음.
		dp[0][0] = map[0][0];
		for(int i = 1; i < M; i++)
			dp[0][i] = dp[0][i - 1] + map[0][i];
		
		for(int i = 1; i < N; i++) {
			//왼
            temp[0][0] = dp[i - 1][0] + map[i][0];
            for(int j = 1; j < M; j++)
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + map[i][j];
            //우
            temp[1][M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
            for(int j = M - 2; j >= 0; j--)
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + map[i][j];
            
            for(int j = 0; j < M; j++)
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
		}
		System.out.println(dp[N - 1][M - 1]);
	}
}
