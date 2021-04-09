package algo210410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <문제 요약> 
 * 구해야 하는 것: 적록 색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하기 
 * 제약 사항: 1 <= N <= 10
 * 문제 유형: bfs, flood-fill
 * 요구 개념: bfs
 * 
 * <풀이법 요약> 
 * 1. 전체적으로 bfs 해보기 => flood-fill
 * => 방문 배열 두개 만들기(적록색약일 때, 적록색약 아닐 때)
 *
 */
public class BOJ_Gold5_10026_적록색약 {

	static int N;
	static char[][] map;
	static int[][] RGBvisited, RRBvisited;
	static int RGBIdx, RRBIdx;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		map = new char[N][N];
		RGBvisited = new int[N][N];
		RRBvisited = new int[N][N];
		
		StringTokenizer st = null;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			String str = st.nextToken();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		bfsRGB();
		
		bfsRRB();
		
		System.out.println(RGBIdx+" "+RRBIdx);
	}

	private static void bfsRRB() {
		queue.clear();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(RRBvisited[r][c] != 0) continue;
				++RRBIdx;
				
				char cur = map[r][c];
				queue.offer(new int[] {r,c});
				
				RRBvisited[r][c] = RRBIdx;
				
				while(!queue.isEmpty()) {
					int curR = queue.peek()[0];
					int curC = queue.peek()[1];
					queue.poll();
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = curR + dr[dir];
						int nc = curC + dc[dir];
						
						if(!check(nr, nc) || RRBvisited[nr][nc] != 0) continue;
						if(!RRBcheck(cur, nr, nc)) continue;
						RRBvisited[nr][nc] = RRBIdx;
						
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}

	private static boolean RRBcheck(char cur, int nr, int nc) {
		if(cur == 'R' || cur == 'G') {
			if(map[nr][nc] == 'R' || map[nr][nc] == 'G') return true;
			else return false;
		} else {
			if(map[nr][nc] == cur) return true;
			else return false;
		}
	}

	private static void bfsRGB() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(RGBvisited[r][c] != 0) continue;
				++RGBIdx;
				
				char cur = map[r][c];
				queue.offer(new int[] {r,c});
				
				RGBvisited[r][c] = RGBIdx;
				
				while(!queue.isEmpty()) {
					int curR = queue.peek()[0];
					int curC = queue.peek()[1];
					queue.poll();
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = curR + dr[dir];
						int nc = curC + dc[dir];
						
						if(!check(nr, nc) || map[nr][nc] != cur || RGBvisited[nr][nc] != 0) continue;
						RGBvisited[nr][nc] = RGBIdx;
						
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
