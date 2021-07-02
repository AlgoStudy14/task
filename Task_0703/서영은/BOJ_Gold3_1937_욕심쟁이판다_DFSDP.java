package algo210703;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold3_1937_욕심쟁이판다_DFSDP {
	
	static int n, map[][], dp[][], maxDay;
	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		n = Integer.parseInt(in.readLine());
		
		map = new int[n][n];
		dp = new int[n][n]; // [r,c] 좌표에서 판다가 출발하면 z년 생존한다.
		maxDay = -1;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				maxDay = Math.max(maxDay, findArea(r, c));
			}
		}
		
		bw.write(maxDay+"\n");
		
		in.close();
		bw.flush();
		bw.close();
	}

	private static int findArea(int r, int c) {
		
		if(dp[r][c] != 0) {
			return dp[r][c];
		}
		
		dp[r][c] = 1;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc) || map[nr][nc] <= map[r][c]) continue;
			
			dp[r][c] = Math.max(dp[r][c], findArea(nr, nc) + 1);
		}
		
		return dp[r][c];
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}
}
