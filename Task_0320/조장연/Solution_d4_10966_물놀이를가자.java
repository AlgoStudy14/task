import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 땅과 물을 표현하는 map이 주어졌을 때 각 땅에서 가장 가까운 물로가는 거리의 합 구하기
 * 
 * <풀이법 요약>
 * BFS를 이용하여 풀었음.
 * 
 * 시도1 : 땅과 물의 좌표를 분류한다음 땅을 기준으로 모든 물의 좌표와 거리 비교해서 최소값 뽑아서 더함(완탐)
 * => 시간초과!!!
 * 시도2 : 땅을 기준으로 BFS를 돌리면서 최소값을 구해서 더함
 * => 시간초과!!
 * 시도3 : 모든 물을 큐에 때려박고 한번에 bfs실행
 * => 정답!!
 * 
 * **기계적으로 BFS, DFS를 구현했는데 어떤것을 기준으로 어떤식으로 돌릴지를 생각하고 문제에 접근하면 좋을 것 같다.(쉬운 문제 같았는데 너무 많이 박았음)**
 */

public class Solution_d4_10966_물놀이를가자 {
	static int T, N, M;
	static int answer;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int count;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		T = Integer.parseInt(sc.nextLine());

		for (int t = 1; t <= T; t++) {
			String[] nm = sc.nextLine().split(" ");
			N = Integer.parseInt(nm[0]);
			M = Integer.parseInt(nm[1]);
			map = new char[N][M];
			boolean[][] visited = new boolean[N][M];
			Queue<int[]> queue = new LinkedList<>();
			answer = 0;
			count = 0;
			int number = 0;
			int[] flag = { -1, -1 };
			queue.offer(flag);

			for (int i = 0; i < N; i++) {
				String str = sc.nextLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'W') {
						count++;
						int[] arr = { i, j };
						queue.add(arr);
						visited[i][j] = true;
					}
				}
			}

			while (!queue.isEmpty()) {
				int[] position = queue.poll();

				if (position[0] == -1) {
					number++;
					queue.offer(flag);
					continue;
				}

				boolean checkVisited = false;
				for (int k = 0; k < 4; k++) {
					if (check(position[0] + dr[k], position[1] + dc[k])) {
						if (map[position[0] + dr[k]][position[1] + dc[k]] == 'L'
								&& visited[position[0] + dr[k]][position[1] + dc[k]] == false) {
							int[] arr = { position[0] + dr[k], position[1] + dc[k] };
							queue.offer(arr);
							visited[position[0] + dr[k]][position[1] + dc[k]] = true;
							answer += number;
							count++;
						}
					}

				}

				if (count == N * M)
					break;
			}

			System.out.println("#" + t + " " + answer);
		}
	}


	private static boolean check(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}
}
