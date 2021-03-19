package algo210320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {

	static int T, N, M, R, C, L, answer;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new int[N][M];
			answer=0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			BFS();
			
			checkVisited();
			
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void checkVisited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j] != 0) answer++;
			}
		}
		
	}

	private static void BFS() {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[]{R, C});
		visited[R][C] = 1;
		
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			++cnt;
			if(cnt == L+1) {
				return;
			}
			int cycle = queue.size();
			
			for (int a = 0; a < cycle; a++) {
				int curR = queue.peek()[0];
				int curC = queue.peek()[1];
				queue.poll();
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = curR + dr[dir];
					int nc = curC + dc[dir];
					
					if(!check(nr,nc) || visited[nr][nc] != 0 ) continue;
					if(checkMap(curR, curC, nr, nc, dir)) {
						visited[nr][nc] += visited[curR][curC] + 1;
						queue.offer(new int[] {nr,nc});
					}
				}
			}
		}
	}

	private static boolean checkMap(int curR, int curC, int nr, int nc, int dir) {
		if(map[curR][curC] == 0) {
			return false;
		} else if(map[curR][curC] == 1) {
			if((dir==0 || dir==1 || dir==2 || dir==3) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 2) {
			if((dir==0 || dir==1) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 3) {
			if((dir==2 || dir==3) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 4) {
			if((dir==0 || dir==3) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 5) {
			if((dir==1 || dir==3) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 6) {
			if((dir==1 || dir==2) && checkDir(nr, nc, dir)) return true;
			return false;
		} else if(map[curR][curC] == 7) {
			if((dir==0 || dir==2) && checkDir(nr, nc, dir)) return true;
			return false;
		} 
		return false;
	}

	private static boolean checkDir(int nr, int nc, int dir) {
		if(dir==0) return map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6;
		else if(dir==1) return map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7;
		else if(dir==2) return map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5;
		else return map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7;
	}

	private static boolean check(int r, int c) {
		
		return r>=0 && r<N && c>=0 && c<M;
	}

}
