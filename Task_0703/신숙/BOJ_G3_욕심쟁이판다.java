import java.util.Scanner;

public class BOJ_G3_욕심쟁이판다 {
	/*
	 * <문제>
	 * N * N 대나무 숲에서 대나무를 먹으면 상 하 좌 우로 이동 가능. 다만 이전에 먹은 위치보다 더 많은 대나무가 있어야함.
	 * 최대한 오래 살 수 있는 일 수 를 출력하라.
	 * 
	 * <문제풀이>
	 * 저번에 푼 경사로랑 비슷한 문제인 것 같음.
	 * 1. 현재칸에서 사방으로 이동할수 있는지 확인함.(더 큰 수여야함.)
	 * 2. 이동할 수 있다면 dp에 값이 저장되어있는지 확인하여 이미 있는 경우는 그 수를 더하면 됨.
	 * 3. 최대값인지 비교하여 dp배열에 저장하며 이 값 중 최대값을 결과로 출력
	 * 
	 * <문제점>
	 * 1. 현재 답이 1 작게 나옴.(4 -> 3) 이유는? 함수를 호출할 때 다음 껄 넘어가므로 1부터 시작이여야하는데 0부터 시작을 했음.
	 */
	
	static int N, ans;
	static int[][] dp, map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		dp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				ans = Math.max(ans, dfs(i, j));
		}
		System.out.println(ans);
	}
	
	static int dfs(int i, int j) {
		if(dp[i][j] != 0) {
			return dp[i][j];
		}
		//int temp = 0;		 문제점 1
		int temp = 1;
		for(int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr >= 0 && nc >= 0 && nr < N && nc < N && map[i][j] < map[nr][nc]) {
				temp = Math.max(temp, dfs(nr, nc) + 1);
				dp[i][j] = temp;
			}
		}
		
		return temp;
	}
}
