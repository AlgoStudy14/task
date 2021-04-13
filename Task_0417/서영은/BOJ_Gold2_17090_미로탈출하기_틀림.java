package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
 * => 시간초과 난 풀이!!
 */
public class BOJ_Gold2_17090_미로탈출하기_틀림 {
	
	static int N,M;
	static char[][] map;
	static int[][] allVisited;
	static boolean[][] tempVisited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		allVisited = new int[N][M];
		tempVisited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 처음 칸부터 돌기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				checkGo(i, j);
			}
		}
		
		int cnt=0;
		// 탈출 가능한 칸 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(allVisited[i][j] == 1) ++cnt;
			}
		}
		
		System.out.println(cnt);
	}

	private static void checkGo(int r, int c) {
		initTempVisited();

		int curR = r;
		int curC = c;

		tempVisited[curR][curC] = true;
		
		while (true) {
			// 탈출이 가능한 칸이면
			if (allVisited[curR][curC] == 1) {
				check(1);
				break;
			}
			if (allVisited[curR][curC] == 2) {
				check(2);
				break;
			}

			int nr = nextR(curR, curC);
			int nc = nextC(curR, curC);

			if (checkBoundary(nr, nc)) {
				if (!tempVisited[nr][nc]) {
					tempVisited[nr][nc] = true;
				} else { // 싸이클 생성된 것
					check(2);
					break;
				}
			} else {
				// 탈출
				check(1);
				break;
			}

			curR = nr;
			curC = nc;
		}
	}

	private static int nextR(int curR, int curC) {
		if(map[curR][curC] == 'U') {
			return curR + dr[0];
		} else if(map[curR][curC] == 'R') {
			return curR + dr[1];
		} else if(map[curR][curC] == 'D') {
			return curR + dr[2];
		} else {
			return curR + dr[3];
		}
	}
	
	private static int nextC(int curR, int curC) {
		if(map[curR][curC] == 'U') {
			return curC + dc[0];
		} else if(map[curR][curC] == 'R') {
			return curC + dc[1];
		} else if(map[curR][curC] == 'D') {
			return curC + dc[2];
		} else {
			return curC + dc[3];
		}
	}

	private static boolean checkBoundary(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

	private static void check(int num) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tempVisited[i][j])
					allVisited[i][j] = num;
			}
		}
	}

	private static void initTempVisited() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(tempVisited[i], false);
		}	
	}
}
