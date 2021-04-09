package algo210410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약> 
 * 구해야 하는 것: 물에 잠기지 않은 안전한 영역의 최대 개수
 * 제약 사항: 2<=N<=100, 높이 = 1이상 100 이하
 * 문제 유형: bfs
 * 요구 개념: bfs
 * 
 * <풀이법 요약> 
 * 1. 물에 잠기지 않은 지점 표시
 * 2. flood-fill 이용하여 물에 잠기지 않은 지점 개수 세기
 * 3. 최댓값 찾기
 * 
 * => 물의 잠기지 않을 수도 있다 = 이거를 높이의 최솟값이 1인거랑 헷갈려서 틀렸다..
 *
 */
public class BOJ_Silver1_2468_안전영역 {

	static int N;
	static int[][] map, tempMap;
	static int[][] visited;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	static int maxHeight = Integer.MIN_VALUE, minHeight = Integer.MAX_VALUE, count=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		tempMap = new int[N][N];
		visited = new int[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
				minHeight = Math.min(minHeight, map[i][j]);
			}
		}
		
		for (int rain = maxHeight; rain >= 0 ; rain--) { // 아무데도 잠기지 않을 수도 있다!!
			findSafe(rain);
		}
		
		System.out.println(count);
	}
	
	private static void findSafe(int h) {
		//배열 복사
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, tempMap[i], 0, N);
		}
		
		// 높이가 h보다 작으면 0으로 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tempMap[i][j] <= h) tempMap[i][j] = 0;
			}
		}
		
		countSafe();
	}

	private static void countSafe() {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);
		}
		queue.clear();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tempMap[i][j] == 0 || visited[i][j] != 0) continue;
				++idx;
				visited[i][j] = idx;
				queue.offer(new int[] {i,j});
				
				while(!queue.isEmpty()) {
					int curR = queue.peek()[0];
					int curC = queue.peek()[1];
					queue.poll();
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = curR + dr[dir];
						int nc = curC + dc[dir];
						
						if(!check(nr,nc) || tempMap[nr][nc] == 0 || visited[nr][nc] != 0) continue;
						visited[nr][nc] = idx;
						queue.offer(new int[] {nr,nc});
					}
				}
			}
		}
		count = Math.max(count, idx);
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
