import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_Silver1_2468 {
	/*
	 * 안전영역
	 * 장마로 높이가 1부터 천천히 잠기는데, 기간동안 물에 잠기지 않는 최대 영역 개수 출력.
	 * 
	 * <풀이>
	 * 1. map 입력할 때, 최대값을 저장해두어서 
	 * 2. bfs를 실행시킬때, 최대값만큼 반복.
	 * 3. bfs.
	 * 
	 * 1. 오답. 왜 예제 답이 13일까. => visited 초기화문제 위치를 바꿔줘도 그러네.
	 * 2. 아 while문 돌 때 내부에서 nr = 빼온것의 r + dr[i]인데, r을 그냥 넣어줫음.
	 * 3. 81%에서 오답.	=> ???
	 * 노트 : 아무 지역도 물에 잠기지 않을 수도 있다.
	 * 4. 아... h를 1로하면 안되네...
	 */
	
	static int N, max, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<int[]> q;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		max = Integer.MIN_VALUE;
		int cnt = 0;
		int index=0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				index = sc.nextInt();
				map[i][j] = index;
				max = Math.max(max, index);
			}
		}
		
		for(int h = 0; h <= max; h++) {
			cnt = 0;
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++ ) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > h && !visited[i][j]) {
						cnt++;
						bfs(h, i, j);
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}
	
	static void bfs(int h, int r, int c) {
		q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		//int now[];
		//int nr = 0, nc = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] > h) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}
