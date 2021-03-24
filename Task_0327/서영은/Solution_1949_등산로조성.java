package algo210327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * <문제 요약>
 * 구해야 하는 것: 만들 수 있는 가장 긴 등산로 길이 찾기 
 * 제약 사항: 딱 한곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.
 * 가장 높은 봉우리에서 등산로 시작, 높은 지형에서 낮은 지형으로 가로 세로로만 이동 가능 
 * 문제 유형: dfs(백트래킹, 완탐) -> bfs로 풀어보려다 안돼서 바꿈!
 * 요구 개념: dfs
 * 
 * <풀이법 요약>
 * 1. dfs를 이용한 풀이법
 * 2. 먼저 가장 높은 봉우리 찾아서 maxHeight 리스트에 넣어주기 
 * 3. 앞에 길이 작으면 그냥 가고, 크면 한번 깎아보기! 한번 깎은 후에는 되돌리기 전까지 깎을 수 없으므로 전역 use 변수 이용해서 관리 
 * 
 */
public class Solution_1949_등산로조성 {

	static int T,N,K,answer;
	static int[][] map;
	static boolean[][] visited;
	static boolean use; // 한군데 팠는지 체크
	static ArrayList<int[]> maxHeight; // 가장 높은 봉우리들 찾아서 넣어주기 
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,-1,1};
	static int startR, startC;	// 시작점 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			maxHeight = new ArrayList<int[]>();
			answer=Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//로직 
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, map[i][j]);
				}
			}
			
			// 가장 높은 봉우리 좌표 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(max == map[i][j]) maxHeight.add(new int[] {i,j});
				}
			}
			
			//로직 -> 높은 봉우리 하나씩 돌아보기 
			for(int[] start : maxHeight) {
				startR = start[0];
				startC = start[1];
				visited[startR][startC] = true;
				dfs(startR, startC, 1);
				visited[startR][startC] = false;
			}
			
			System.out.println("#"+t+" "+answer);
		}

	}

	private static void dfs(int r, int c, int cnt) {
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc) || visited[nr][nc] || (startR==nr && startC==nc)) continue;
			
			if(map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				dfs(nr, nc, cnt+1);
				visited[nr][nc] = false;
				// 다음 칸이 클 때 
			} else if(map[nr][nc] >= map[r][c]
					&& !use && map[nr][nc]-K < map[r][c] && map[r][c] >= 1) {
				//땅 파보기
				use = true;
				int tempHeight = map[nr][nc];
				map[nr][nc] = map[r][c]-1;
				visited[nr][nc] = true;
				
				dfs(nr,nc, cnt+1);
				
				// 땅 되돌리기 
				use = false;
				map[nr][nc] = tempHeight;
				visited[nr][nc] = false;
			}
		}
		answer = Math.max(answer, cnt);
		return;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
