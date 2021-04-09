import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_Gold5_10026 {
	/*
	 * 적록색약
	 * 
	 * <문제>
	 * map에 R,G,B가 주어짐. 적록색약은 적색과 녹색이 붙어있는 경우 영역이 1개라고 보임.
	 * 일반인이 볼 때, MAP의 영역 개수와 적록색약이 볼 때의 MAP 영역개수 출력
	 * 
	 * <풀이>
	 * 1. BFS...를 돌리는데 적록색약은 다음 것을 확인할 때, red + green 은 같은것으로 취급하여 영역두름.?
	 * 2. 내부 조건문을 다시 설정해주려다가 그냥 일반인 후에 맵을 바꿔줌.
	 */
	static int N, nor, redGreen, cnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = sc.next(); 
			for(int j = 0; j < N; j++)
				map[i][j] = s.charAt(j);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					nor++;
					nor_bfs(i, j, map[i][j]);
				}
			}
		}
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 'G')
					map[i][j] = 'R';
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					redGreen++;
					nor_bfs(i, j, map[i][j]);
				}
			}
		}
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				if(!visited[i][j]) {
//					redGreen++;
//					green_bfs(i, j, map[i][j]);
//				}
//			}
//		}
		System.out.println(nor + " " + redGreen);
	}
	
	static void nor_bfs(int r, int c, char mapC) {
		Queue<int[]> q1 = new LinkedList<int[]>();
		q1.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q1.isEmpty()) {
			int[] now = q1.poll();
			for(int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i]; 
				
				if(nr < N && nr >= 0 && nc < N && nc >= 0 && !visited[nr][nc] && map[nr][nc] == mapC) {
					visited[nr][nc] = true;
					q1.offer(new int[] {nr, nc});
				}
			}
		}
	}
	/*
	static void green_bfs(int r, int c, char mapC) {
		Queue<int[]> q2 = new LinkedList<int[]>();
		q2.offer(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q2.isEmpty()) {
			int[] now = q2.poll();
			for(int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i]; 
				
				//조건문을 어떻게하지?
				if(nr < N && nr >= 0 && nc < N && nc >= 0 && !visited[nr][nc] && map[nr][nc] == mapC) {
				
					visited[nr][nc] = true;
					q2.offer(new int[] {nr, nc});
				}
			}
		}
	}
	*/
}