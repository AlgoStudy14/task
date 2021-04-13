package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 탈출 가능한 칸의 수
 * 제약 사항: N, M(3<=N,M<=500)
 * 문제 유형: 시뮬레이션?
 * 요구 개념: 
 * 
 * <풀이법 요약>
 * 탈출 못하는 경우 -> 싸이클이 생기는 경우!
 * 탈출 하는 경우 -> 탈출하면서 거쳐갔던 경로들에 있는 칸들은 무조건 탈출 가능한 칸!
 * => 싸이클이 생기는 경우와 탈출할 수 있는 경우를 어떻게 처리하느냐가 관건.(시간복잡도 단축)
 * => allVisited 배열에 싸이클이 생기는 칸은 2로 체크. 경로들에 있는 칸들은 1로 체크  
 * 
 * => 이렇게 했더니 틀림... 가장 가장자리에 있는 칸부터 체크하자!
 * => 맞는 풀이
 * 
 */
public class BOJ_Gold2_17090_미로탈출하기 {
	
	static int N,M;
	static char[][] map;
	static boolean[][] allVisited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		allVisited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 가장자리 칸 중 탈출 가능한 칸 체크
		for (int i = 0; i < N; i++) {
			if(i==0 || i==N-1) {
				for (int j = 0; j < M; j++) {
					checkOut(i, j);
				}
			} else {
				checkOut(i, 0);
				checkOut(i, M-1);
			}
		}
		
		//가장자리에서 탈출 가능한 칸부터 경로 체크
		for (int i = 0; i < N; i++) {
			if(i==0 || i==N-1) {
				for (int j = 0; j < M; j++) {
					if(allVisited[i][j]) {
						checkPath(i, j);
					}
				}
			} else {
				if(allVisited[i][0]) {
					checkPath(i, 0);
				}
				if(allVisited[i][M-1]) {
					checkPath(i, M-1);
				}
			}
		}
		
		int cnt=0;
		// 탈출 가능한 칸 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(allVisited[i][j]) ++cnt;
			}
		}
		
		System.out.println(cnt);
	}

	private static void checkPath(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(new int[] {r,c});
		while(!queue.isEmpty()) {
			int curR = queue.peek()[0];
			int curC = queue.peek()[1];
			queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = curR + dr[dir];
				int nc = curC + dc[dir];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M) {
					if(allVisited[nr][nc]) continue;
					if(check(nr, nc, dir)) {
						allVisited[nr][nc] = true;
						queue.offer(new int[] {nr,nc});
					}
				}
			}

		}
	}

	private static boolean check(int nr, int nc, int dir) {
		if(dir == 0 && map[nr][nc]=='D') return true;
		else if(dir == 1 && map[nr][nc] == 'L') return true;
		else if(dir == 2 && map[nr][nc] == 'U') return true;
		else if(dir == 3 && map[nr][nc] == 'R') return true;
		else return false;
	}

	private static void checkOut(int r, int c) {
		int nr=0, nc=0;
		switch (map[r][c]) {
		case 'U':
			nr = r + dr[0];
			nc = c + dc[0];
			break;
		case 'R':
			nr = r + dr[1];
			nc = c + dc[1];
			break;
		case 'D':
			nr = r + dr[2];
			nc = c + dc[2];
			break;
		case 'L':
			nr = r + dr[3];
			nc = c + dc[3];
			break;
		default:
			break;
		}
		
		if(nr<0 || nr>=N || nc<0 || nc>=M) {
			allVisited[r][c] = true;
		}
	}
}
