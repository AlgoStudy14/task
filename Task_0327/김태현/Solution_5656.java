import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 규칙에 따라 최대한 많은 벽돌을 제거하였을 때 남은 벽돌의 개수.
 * 요구 개념/문제 유형 : 구현, 시뮬레이션, 중복 순열, 좌표, 방향 등...
 * <풀이법>
 * 1. 중복 순열을 이용하여 공을 떨어뜨린 위치를 정한다.
 * 2. 각 중복 순열에 대하여 주어진 조건대로 시뮬레이션 한다.
 * -> 공을 떨어뜨린 해당 열의 가장 위 좌표를 큐에 넣고, 삭제한다.
 * -> 이후 큐에서 나오는 순서대로 각 벽돌에 대한 동시 폭발 벽돌을 모두 큐에 넣고, 삭제한다.
 * -> 큐가 모두 빈다면, 벽돌을 아래 방향으로 떨어뜨린다.
 * -> 남은 최소 벽돌의 개수를 갱신한다.
 * <주의점>
 * 시뮬레이션 문제이다. DFS 방식으로 연쇄 반응을 처리하는게 더 깔끔한거 같다.
 * 시뮬레이션은 처음부터 조건을 꼼꼼히 따져가며 코드를 작성하도록 항상 주의하자.
 * ->특히, 이 문제와 같이 input이 아주 긴 경우는 스스로 input case를 만들어 볼 수도 있어야 한다.
 */
public class Solution_5656 {
	static int T, N, W, H;
	static int[][] map;
	static int[][] simul_map;
	static boolean[][] visited;
	// 중복 순열로 선택된 공 투하 위치를 담을 스택
	static Stack<Integer> seq;
	// 최소 블럭 개수
	static int min;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			seq = new Stack<Integer>();
			min = Integer.MAX_VALUE;

			// 중복 순열
			permutation_with_repetition(0);

			System.out.printf("#%d %d\n", t, min);
		}
	}

	private static void permutation_with_repetition(int cnt) {
		// N개를 선택했으면, 해당 경우에 대하여 시뮬레이션 진행.
		if (cnt == N) {
			simulation();
			return;
		}
		for (int i = 0; i < W; i++) {
			seq.push(i);
			permutation_with_repetition(cnt + 1);
			seq.pop();
		}
	}

	private static void simulation() {
		// 시뮬레이션을 진행할 맵 설정.
		simul_map = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				simul_map[i][j] = map[i][j];
			}
		}
		// 투하되는 공의 개수 만큼 반복한다.
		for (int i = 0; i < seq.size(); i++) {
			visited = new boolean[H][W];
			// 공을 떨어뜨린 해당 열의 가장 위 좌표를 큐에 넣는다.
			Queue<int[]> q = new LinkedList<int[]>();
			q.offer(find_top(seq.get(i)));
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				if (cur == null) {
					continue;
				}
				// 이후 큐에서 나오는 순서대로 각 벽돌에 대한 동시 폭발 벽돌을 모두 큐에 넣고, 해당 블록들은 삭제한다.
				// 상하좌우
				for (int a = 0; a < 4; a++) {
					// 폭발 반경
					for (int b = 0; b < cur[2]; b++) {
						int nr = cur[0] + dr[a] * b;
						int nc = cur[1] + dc[a] * b;
						if (check_boundary(nr, nc) && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new int[] { nr, nc, simul_map[nr][nc] });
						}
					}
				}
				simul_map[cur[0]][cur[1]] = 0;
			}

			// 공을 아래로 떨어뜨린다.
			// 열 단위로 진행
			for (int col = 0; col < W; col++) {
				// 열의 0이 아닌 모든 값을 스택에 넣고 해당 위치는 0으로 바꾼다.
				Stack<Integer> rest = new Stack<Integer>();
				for (int row = 0; row < H; row++) {
					if (simul_map[row][col] != 0) {
						rest.push(simul_map[row][col]);
						simul_map[row][col] = 0;
					}
				}
				// 스택이 빌 때까지 행의 제일 높은 위치부터 차곡차곡 배치한다.
				int idx = H - 1;
				while (!rest.isEmpty()) {
					simul_map[idx--][col] = rest.pop();
				}
			}

		}
		// 남은 공의 개수를 세고 갱신한다.
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (simul_map[i][j] != 0) {
					cnt++;
				}
			}
		}
		min = Math.min(min, cnt);
	}

	private static boolean check_boundary(int nr, int nc) {
		if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
			return true;
		}
		return false;
	}

	private static int[] find_top(Integer col) {
		for (int i = 0; i < H; i++) {
			if (simul_map[i][col] != 0) {
				// 행, 열, 폭발 반경
				return new int[] { i, col, simul_map[i][col] };
			}
		}
		return null;
	}
}
