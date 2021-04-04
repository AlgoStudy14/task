package s0410;

import java.util.Scanner;

/*
 * <문제 요약>
 * 구해야 하는 것 : 가로선을 최소한으로 추가하여 각 열이 각 열로 내려가도록 사다리를 조작하는 방법.
 * 문제 유형 : 시뮬레이션, DFS 백트래킹.
 * <풀이법>
 * 1. 방문 배열을 만들어 사다리가 놓인 위치를 초기화한다.
 * 2. 사다리의 개수를 1, 2, 3개로 놓아 보면서 DFS 백트래킹을 진행한다.
 * -> 사다리를 연결하는 두 원소 중 하나라도 체크가 되어 있다면 사다리를 놓지 않는다(가지 치기).
 * -> 사다리가 원하는 개수 만큼 놓여진다면 시뮬레이션을 진행한다(각 열에서 열로 가는지).
 * -> 만일 시뮬레이션 결과가 올바르다면 전체 재귀를 종료한다(최소 입력 개수).
 * -> 그렇지 않고 3개를 놓는 DFS까지 완료된다면 -1을 출력한다.
 * <주의사항>
 * 사다리를 놓을 때 방향을 미리 지정해주는 것이 좋다. 이 방향을 지정해주지 않는다면 엄청난 하드 코딩을 해야할 것 같다.
 * 특히 구현 문제에서는, 내가 코딩하는 방향이 하드 코딩이라는 생각이 든다면 멈추고 다른 방향을 생각해보는 습관을 가지자.
 */
public class BOJ_G4_15684_사다리조작 {
	static int N, M, H;
	static int[][] map;
	static boolean flag;
	static int ans;

	public static void main(String[] args) {
		// 초기화.
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 세로선.
		M = sc.nextInt();
		H = sc.nextInt(); // 가로선.
		sc.nextLine();
		map = new int[H][N];
		// 사다리 놓기.
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			// 1은 오른쪽으로
			map[a][b] = 1;
			// 2는 왼쪽으로
			map[a][b + 1] = 2;
		}
		flag = false;
		ans = -1;

		// 사다리 1, 2, 3에 대해서 시뮬레이션 진행하기.
		for (int i = 0; i <= 3; i++) {
			dfs(0, i);
		}
		// 결과 출력.
		System.out.println(ans);
		sc.close();
	}

	private static void dfs(int cur, int cnt) {
		if (flag) {
			return;
		}
		// 만일 사다리의 개수가 충족된다면, 시뮬레이션 이후 재귀 종료.
		if (cur == cnt) {
			if (simulation()) {
				flag = true;
				ans = cnt;
			}
			return;
		}
		// 맵의 좌상단부터 우하단까지 사다리 놓아보기.
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				// 사다리를 놓는 위치에 사다리가 존재하지 않는 경우에만 사다리를 놓고 재귀 진행.
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					// 사다리 두기.
					map[i][j] = 1;
					map[i][j + 1] = 2;
					dfs(cur + 1, cnt);
					// 만일 사다리를 제대로 놓는 경우를 발견하면 전체 재귀를 탈출한다.
					if (flag) {
						return;
					}
					// 사다리 빼기.
					map[i][j] = 0;
					map[i][j + 1] = 0;
				}
			}
		}
	}

	// 각 열에서 열로 가는지 체크.
	private static boolean simulation() {
		// 모든 열에 대하여 반복한다.
		for (int i = 0; i < N; i++) {
			int row = 0;
			int col = i;
			while (row < H) {
				// 만일 현재 위치에 가로 사다리가 놓여 있다면
				if (map[row][col] == 1) { // 오른쪽 이동
					col++;
				} else if (map[row][col] == 2) {
					col--;
				}
				// 아래로 이동.
				row++;
			}
			// 만일, 열이 같은 위치로 내려오지 않았다면, 시뮬레이션 종료.
			if (col != i) {
				return false;
			}
		}
		// 만일 모두 같은 위치로 내려왔다면, 시뮬레이션 성공.
		return true;
	}
}
