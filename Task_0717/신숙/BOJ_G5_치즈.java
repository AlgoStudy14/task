import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G5_치즈 {
	/*
	 * <문제>
	 * N * M 칸에 치즈가 1로 주어짐.
	 * 내부에 막혀있는 공간으로 공기가 있어도 그 주변이 녹진 않고, 외부에 노출된 테두리만 1칸씩 녹음.
	 * 몇시간 녹아서 없어지는지, 또 그 다음 줄에 모두 녹기 바로 전에 몇 칸의 치즈가 있는지 출력.
	 * 
	 * <문제풀이>
	 * BFS문제. 1을 입력하며 치즈의 칸 개수를 입력하여 지울때마다 개수를 지움. 
	 * 치즈가 다 녹아서 개수가 0이 아니라면 다시 bfs를 돌리는데 bfs돌리기전에 답을 저장해야함.
	 * 내부에 0이있다고 그 주변 테두리를 지우면 안되니까 제일 바깥을 해야하는데...
	 * 그림으로 보면 map 제일 테두리가 무조건 0으로 주어지는듯?	=> 0, 0부터
	 */
	
	static int N, M, cheeseN, Tans, Nans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		int inputV = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				inputV = sc.nextInt();
				if(inputV == 1)
					cheeseN++;
				map[i][j] = inputV;
			}
		}
		//치즈가 0이 아니라면 시간 늘려주고 현재 남은 개수 저장해주고
		while(cheeseN != 0) {
			Nans = cheeseN;
			Tans++;
			bfs();
		}
		System.out.println(Tans);
		System.out.println(Nans);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		//무한으로 뺑글뺑글 도는걸 막기 위한 배열
		visited = new boolean[N][M];
		visited[0][0] = true;
		//q가 비어있는게 아니라면 
		while(!q.isEmpty()) {
			int[] point = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = point[0] + dr[d];
				int nc = point[1] + dc[d];
				//0이여도 다음으로 움직여야하니 map 값비교는 X
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
					if(map[nr][nc] == 0)
						q.offer(new int[] {nr, nc});
					//여기에서 이동을 하면 내부를 또 지우게 되므로 이동은 X
					else {
						cheeseN--;
						map[nr][nc] = 0;
					}
					visited[nr][nc] = true;
				}
			}
		}
	}
}
