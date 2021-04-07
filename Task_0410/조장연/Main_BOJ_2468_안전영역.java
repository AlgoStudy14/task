import java.util.Scanner;

/*
 * <문제 요약>
 * 2차원 배열 영역에 각각의 높이를 주어주고 비의 양에 따라 만들어지는 섬갯수 최대 구하기
 * 
 * <풀이법 요약>
 * 비의 양이 0일때부터 max-1일때까지 잠기는 영역을 미리 visited영역에 표시해가며 bfs로 섬 갯수 탐색
 * 비의 양에 따라 초기 설정을 다르게 해주고 bfs를 돌리면서 계속 visited영역을 바꾸기 때문에 첨부터 visited배열을 2개 만들었음
 */

public class Main_BOJ_2468_안전영역 {
	static int N;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static boolean[][] copyVisited;
	static int max;
	static int answer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		max = Integer.MIN_VALUE;
		answer = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				max = Math.max(max, map[i][j]);
			}
		}

		for (int k = 1; k < max; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == k) {
						visited[i][j] = true;
					}
				}
			}

			findAns();

		}

		System.out.println(answer);
	}

	private static void findAns() {
		int count = 0;

		copyVisited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyVisited[i][j] = visited[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyVisited[i][j] == false) {
					count++;
					findland(i, j);
				}
			}
		}

		answer = Math.max(count, answer);
	}

	private static void findland(int i, int j) {
		copyVisited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (check(i + dr[k], j + dc[k])) {
				if (copyVisited[i + dr[k]][j + dc[k]] == false)
					findland(i + dr[k], j + dc[k]);
			}
		}

	}

	private static boolean check(int i, int j) {
		if (i < 0 || j < 0 || i >= N || j >= N)
			return false;
		return true;
	}

}
