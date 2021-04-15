package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: i번 세로선의 결과가 i번이 나오도록 사다리 게임을 조작할 때, 추가해야 하는 가로선 개수의 최소값을 출력
 * 제약 사항: 1<=a<=H, 1<=b<=N-1
 * 문제 유형: 백트래킹, 브루트포스
 * 요구 개념: 백트래킹, 시뮬레이션
 * 
 * <풀이법 요약>
 * 1. 사다리 위치 => 이차원 배열로 체크 => 처음에 그냥 boolean형으로 체크하면 될줄 알았지만 아니었다.. 
 * 2. 내려가다가 사다리 만나면 지나가기.
 * 3. 놓는 사다리 수 1,2,3개 탐색 넣어보기
 * 4. 사다리 수 만나면 dfs 끝내기
 * 
 * => 사다리 int형 배열로! -> 1이면 오른쪽, 2면 왼쪽으로 가는 것
 * 사다리에 사다리 놔준 뒤에 다시 되돌려줘야함! 안 놓았던 것처럼!!
 * 
 */
public class BOJ_Gold4_15684_사다리조작 {
	
	static int N, M, H, count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[H+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = 1;
			map[a][b+1] = 2;
		}
		
		boolean success = false;
		
		if(checkFinish(map)) {
			success = true;
			count = 0;
		} else {
			for (count = 1; count <= 3; count++) {
				if(dfs(0, map)) {
					success = true;
					break;
				}
			}
		}
		
		if(success) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}
	}

	// return true -> 끝난 것!
	private static boolean dfs(int cnt, int[][] map) {
		if(cnt == count) {
			if(checkFinish(map)) return true;
			return false;
		}
		
		for (int r = 1; r <= H ; r++) {
			for (int c = 1; c <= N-1; c++) {
				if(map[r][c] == 0 && map[r][c+1] == 0) {
					map[r][c] = 1;
					map[r][c+1] = 2;
					
					if(dfs(cnt+1, map)) return true;
					// 맵 되돌려주기 필수!!!
					map[r][c] = 0;
					map[r][c+1] = 0;
				}
			}
		}
		return false;
	}

	private static boolean checkFinish(int[][] map) {
		int same = 0;
		for (int startC = 1; startC <= N; startC++) {
			int r = 1;
			int c = startC;
			while(r<=H) {
				if(map[r][c] == 1 && c+1<=N) {
					c=c+1;
					++r;
				} else if(map[r][c] == 2 && c-1 >= 0){
					c=c-1;
					++r;
				} else {
					++r;
				}
			}
			
			if(c == startC) ++same;
			else return false;
		}
		
		if(same == N) return true;

		return false;
	}

}
