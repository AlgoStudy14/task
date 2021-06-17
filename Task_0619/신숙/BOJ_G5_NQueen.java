import java.util.Scanner;

public class BOJ_G5_NQueen {
	/*
	 * <문제>
	 * 크기가 N * N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 구하는 문제.
	 * 
	 * <조건>
	 * 1 <= N <= 15
	 * 
	 * <문제풀이>
	 * N*N 체스판에 N개의 퀸을 백트래킹으로 배치해가면서 서로 공격하지 않는 경우(같은 세로, 가로, 대각선)로 dfs(n)의 n이 cnt가 되면 답+1
	 * 		=> N개의 퀸을 N*N에 두기 위해서는 각 세로줄 가로 줄에 1개씩 배치해야함. 그리고 이게 대각선이 겹치면 안됨.	=> 경우의 수 줄여짐.
	 * 		=> 체스판에서 조합?순열?을 하는 것처럼 해당 위치에 퀸을 두는 경우와 안 두는 경우로 진행.
	 * 
	 * <문제점>
	 * 메모리초과...?  N이 클 때 문제가 생기나봄....ㅠㅠ 더 좋은 가지치기가 있어야 할듯 함.
	 */
	
	static int N, ans;
	static boolean[][] chess;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		chess = new boolean[N][N];
		
		dfs(0);
		System.out.println(ans);
	}
	
	static void dfs(int n) {
		if(n == N) {
/*			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(chess[i][j])
						System.out.print("O ");
					else
						System.out.print("X ");
				}
				System.out.println();
			}
			System.out.println();
			ans++;
			return;*/
			ans++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			//체스판 중 해당 위치에 퀸이 없는 경우. dfs(n)일 때 n은 퀸의 개수이나 각 세로줄마다 퀸이 1개는 있어야 하므로 r로도 가능.
			if(!chess[n][i]) {
				//퀸을 두는경우
				chess[n][i] = true;
				//퀸을 두고 잡아먹히지 않는 위치라면 dfs 진행.
				if(can(n, i))
					dfs(n + 1);
				//퀸을 두지 않는 경우 여기엔 진행을 하지 않으면 for문으로 인해 다음 아래칸에 두고 진행하게 됨.
				chess[n][i] = false;
				//이렇게 짤 때, 각 가로마다 체스를 두는 경우는 만들 수 있으나 n == N 일때 체크를 하게 되면 15까지이므로, 시간초과가 날 것 같은데.
				//어디에서 queen이 공격하는 지 확인을 할까.	=> 대각선과 같은 열, 행에 있는지 확인해야함.
			}
		}
	}
	
	static boolean can(int r, int c) {
		//같은 열
		for(int i = 0; i < N; i++) {
			if(i == r)
				continue;
			if(chess[i][c])
				return false;
		}
		//같은 행에 있다면
		for(int i = 0; i < N; i++) {
			if(i == c)
				continue;
			if(chess[r][i]) 
				return false;
		}
		//대각선 왼쪽 위										N = 5, r = 3, c = 2.   r = 2, c = 1.  r = 1, c = 0 
		int min = Math.min(r,  c);
		for(int i = 0; i < min; i++) {
			if(chess[r - i - 1][c - i - 1])
				return false;
		}
		//대각선 왼쪽 아래. 이건 r은 증가하고 c는 줄어드는데 흠..		N = 5, r = 3, c = 4.	r = 4, c = 3
		min = Math.min(N - r - 1, c);
		for(int i = 0; i < min; i++) {
			if(chess[r + i + 1][c - i - 1])
				return false;
		}
		//대각선 우측 하단										 
		min = Math.min(N - r - 1, N - c - 1);
		for(int i = 0; i < min; i++) {
			if(chess[r + i + 1][c + i + 1])
				return false;
		}
		//대각선 우측 상단
		min = Math.min(r, N - c - 1);
		for(int i = 0; i < min; i++) {
			if(chess[r - i - 1][c + i + 1])
				return false;
		}
		return true;
	}
}