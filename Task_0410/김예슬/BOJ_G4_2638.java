package algo0408;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * <문제 요약>
 * 구해야 하는 것 : 주어진 치즈가 모두 녹아 없어지는데 걸리는 시간을 출력
 * 제약 사항 : 모눈종이의 가장자리에는 치즈가 놓이지 않음, 치즈 내부에 있는 공간은 외부 공기와 접촉하지 않음
 * 문제 유형 : 시뮬레이션, BFS
 * <풀이법 요약>
 * 1. 외부 공기와 내부 공기의 분리를 위해 외부 공기를 표시해줌(air 메소드)
 * 2. 외부 공기와 접촉한 치즈가 있으면 녹임(melt 메소드)
 *  2-1. 치즈를 녹일 때, 현재 시간에 녹은 치즈에는 영향을 받으면 안되므로 copy 사용
 *  2-2. 치즈가 다 녹을 때까지 반복
 */

public class BOJ_G4_2638 {

	static int N, M, cheeze, time = 2;
	static int[][] map, copy;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Pos {
		int x, y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == 1) ++cheeze;
			}
		}

		while (cheeze > 0) {
			air(time);
			for (int i = 0; i < N; i++)		// 외부 공기를 표시 후, map을 copy에 복사
				System.arraycopy(map[i], 0, copy[i], 0, M);
			melt(time++);
			for (int i = 0; i < N; i++)		// 치즈를 녹인 후, copy를 map에 복사
				System.arraycopy(copy[i], 0, map[i], 0, M);
		}

		System.out.println(time - 2);
	}

	// 외부 공기를 표시
	static void air(int now) {
		Queue<Pos> Q = new LinkedList<>();
		Q.offer(new Pos(0, 0));
		map[0][0] = now;
		
		while (!Q.isEmpty()) {
			Pos cur = Q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (map[nx][ny] == now || map[nx][ny] == 1) continue;	// map이 now일 경우 이미 방문한 칸
				
				map[nx][ny] = now;
				Q.offer(new Pos(nx, ny));
			}
		}
	}
	
	static void melt(int now) {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j] != 1) continue;			// 치즈가 아니라면
				
				int tmp = 0;							// 현재 치즈가 외부 공기와 몇 번 접촉했는지 카운팅
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					
					// 외부 공기와 접촉했다면
					if (map[nx][ny] > 1) ++tmp;
				}
				
				// 2번 이상 외부 공기와 접촉했다면(map을 변경하게 될 경우, 현재 녹고있는 치즈에도 영향을 받게됨)
				if (tmp >= 2) {
					copy[i][j] = now;
					--cheeze;
				}
			}
		}
	}
}