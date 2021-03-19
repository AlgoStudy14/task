package algo210320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 땅으로 표현된 모든 칸에 대해서, 어떤 물인 칸으로 이동하기 위한 최소 이동 횟수
 * 제약 사항: 격자 밖으로 나가는 이동은 불가능
 * 문제 유형: BFS
 * 요구 개념: BFS 
 * 
 * <풀이법 요약>
 * 1. 모든 물을 큐에 담고, BFS 돌리면 됨
 * -> 유의사항: 다음 자리가 W일 경우에는 체크하면 안된다.
 *
 */
public class Solution_D4_10966_물놀이를가자 {

	static int T,N,M;
	static char[][] map;
	static int[][] visited;
	static int answer;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T=Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			visited = new int[N][M];
			answer=0;
			queue.clear();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				String line = st.nextToken();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if(line.charAt(j)=='W') queue.offer(new int[] {i,j});
				}
			}
			
			//로직 
			BFS();
			
			checkMoveLength();
			
			//출력
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void checkMoveLength() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'L') answer+=visited[i][j];
			}
		}
	}

	private static void BFS() {
		while(!queue.isEmpty()) {
			int curR = queue.peek()[0];
			int curC = queue.peek()[1];
			queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = curR + dr[dir];
				int nc = curC + dc[dir];
				
				if(!check(nr, nc) || visited[nr][nc] != 0 || map[nr][nc] == 'W') continue;
				
				
				visited[nr][nc] = visited[curR][curC] + 1;
				
				queue.offer(new int[] {nr, nc});
			}
		}
	}

	//격자를 벗어나면!
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
