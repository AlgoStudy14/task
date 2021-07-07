package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver1_11048_이동하기 {

	static int N, M, map[][], dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = map[1][1];
		
		bw.write(process()+"\n");
		
		in.close();
		bw.flush();
		bw.close();
		
	}
	
	static int dr[] = {1,0,1};
	static int dc[] = {0,1,1};
	private static int process() {
		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(new int[] {1,1});
		
		while(!queue.isEmpty()) {
			int r = queue.peek()[0];
			int c = queue.peek()[1];
			queue.poll();
			
			for (int dir = 0; dir < 3; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(!checkBoundary(nr, nc)) continue;
				
				if(map[nr][nc] + dp[r][c] > dp[nr][nc]) {
					dp[nr][nc] = map[nr][nc] + dp[r][c];
					queue.offer(new int[] {nr,nc});
				}
			}
		}
		return dp[N][M];
	}
	
	private static boolean checkBoundary(int r, int c) {
		return r>=1 && r<=N && c>=1 && c<=M;
	}
}
