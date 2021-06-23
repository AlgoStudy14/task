import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S1_미로탐색 {
	/*
	 * <문제>
	 * 0,0 에서 입력받은 N, M 으로 이동하는데 걸리는 최단 거리구하는 문제.
	 * 항상 갈수 있는 경우만 입력으로 주어져 예외처리는 필요없다.
	 * 
	 * <풀이>
	 * 1. (0,0)에서 시작으로 BFS를 이용하여 다음 칸이 0이 아닌 1일 경우 이동 가능 Queue에 넣음.
	 * 2. 이미 이동한 곳은 다른 int 배열로 관리하여 와리가리 못하게 막아두는 겸, 배열에 이동하는데 걸리는 수를 넣어서 관리
	 * 3. 마지막에 N, M 값 출력
	 * 
	 * <문제점>
	 * 처음 생각한 것은 두 번째 배열에서 갈수 있는지와 함께 거리로 판단하려고 했더니 nr nc 구하는 곳에서 값 더하는게 조금 귀찮아짐.
	 * boolean으로 변경 후, map의 값을 변경시키도록 함.
	 */
	static int N, M;
	static int[][] map;
	static boolean[][] can;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		can = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);
		System.out.println(map[N - 1][M - 1]);
	}
	
	static void bfs(int n, int m) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {n, m});
		
		int dist = 0;
		while(!q.isEmpty()) {
			int[] pop = q.poll();
			can[n][m] = true;
			
			//dist++;
			//num[pop[0]][pop[1]] = dist;
			
			for(int d = 0; d < 4; d++) {
				int nr = pop[0] + dr[d];
				int nc = pop[1] + dc[d];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != 0 && can[nr][nc] == false) {
					q.offer(new int[] {nr,nc});
					can[nr][nc] = true;
					map[nr][nc] = map[pop[0]][pop[1]] + 1;
				}
			}
		}
	}
}
