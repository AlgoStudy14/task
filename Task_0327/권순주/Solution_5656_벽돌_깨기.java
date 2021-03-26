import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 최대한 많은 벽돌을 제거한 뒤의 남은 벽돌의 개수
 * 문제 핵심 요약 : 시뮬레이션 (dfs + bfs + stack)
 * 
 * <풀이법 요약>
 * bfs 부분보다는 solve가 핵심인 것 같아서 solve 위주로 설명
 * 기존의 map을 계속 변경하기 때문에 안 좋은 코드 인 것 같음
 * copy해서 사용하는게 많기도 하고 실제로 메모리도 많이 씀 -> 더 좋은 방법 무조건 있음!
 * 넓게 보자면 map 바꾸기 -> dfs -> map 되돌리기
 * 
 * 1. 기존 map을 tmp에 저장
 * 2. 해당 위치에 구슬을 놓았을 때의 map으로 변경
 * 3. dfs (사용한 구슬의 개수와 깨진 벽돌의 수를 가지고 다님)
 * 4. map을 1번으로 변경 (reset)
 * 
 * 이러니까 모두 다 깨졌을 때 음수가 나옴 + 함수가 너무 많이 돌아감
 * 3. dfs에 조건 추가
 * 3-1. 만약 남은 벽돌이 존재한다면 -> dfs
 * 3-2. 만약 남은 벽돌이 존재하지 않는다면 -> answer = 0 + return
 * 
 * 
 * 설명하라면 못 할듯,,,
 * A형 안녕~~
 */

public class Solution_5656_벽돌_깨기 {

	static class Point {
		int x;
		int y;
		int dis;

		public Point(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}

	static int[][] map;
	static int N, W, H;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Stack<Integer> stack = new Stack<>();
	static int answer, count, blockCount;
	// answer : 정답
	// count : 원하는 위치에 구슬을 위치했을 때 깨진 벽돌의 수
	// blockCount : 처음 벽돌의 총 개수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			blockCount = 0; // 처음 벽돌의 총 개수 저장
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						blockCount++; // 처음 벽돌의 개수 저장
					}
				}
			}
			answer = blockCount; // answer에 처음 벽돌의 총 개수 저장 -> 나중에 줄여나감
			solve(0, 0);
			System.out.printf("#%d %d\n", t, answer);
		}
	}

	// solve 함수
	// cnt : 사용한 구슬의 개수
	// sum : 총 깨지는 벽돌의 개수
	private static void solve(int cnt, int sum) {
		if (cnt == N) { // 구슬을 다 사용했을 때
			answer = Math.min(answer, blockCount - sum); // answer 저장
			return;
		}
		for (int i = 0; i < W; i++) {
			int[][] tmp = copy(); // 원래 가지고 있었던 map 저장
			map = bomb(findX(i), i); // i행에 구슬을 놓았을 때, 벽돌들이 제거된 후의 map
			if ((blockCount - sum) != count) { // 만약 구슬이 다 깨지지 않았다면
				solve(cnt + 1, sum + count); // cnt + 1 (구슬 추가), sum + count (깨진 벽돌의 수 추가)
			} else { // 만약 구슬이 다 깨졌다면
				answer = 0; // 남은 벽돌의 개수 = 0
				return; // 끝!
			}
			map = tmp; // solve가 끝나면 map 다시 원래대로 돌려놓기
		}
	}

	// map copy해서 return
	private static int[][] copy() {
		int[][] tmp = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}

	// y -> 꼭짓점의 x return
	private static int findX(int y) {
		int index = 0;
		for (int i = 0; i < H; i++) {
			if (map[i][y] != 0) {
				index = i;
				break;
			}
		}
		return index;
	}

	// 폭발하고 난 뒤의 map return
	private static int[][] bomb(int x, int y) {
		int[][] tmpMap = copy();
		boolean[][] visited = new boolean[H][W];
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y, tmpMap[x][y]));
		visited[x][y] = true; // 방문 체크
		tmpMap[x][y] = 0; // 깨진곳 0으로 변경
		count = 1; // 깨진 벽돌 수 저장 (이미 꼭짓점 깨졌으니까 1로 시작)

		// 폭발 과정
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < p.dis; j++) { // 길이만큼 반복
					int nx = p.x + dx[i] * j;
					int ny = p.y + dy[i] * j;
					if (nx < 0 || nx >= H || ny < 0 || ny >= W || visited[nx][ny] || tmpMap[nx][ny] == 0) {
						continue;
					}
					q.offer(new Point(nx, ny, tmpMap[nx][ny]));
					visited[nx][ny] = true; // 방문 체크
					tmpMap[nx][ny] = 0; // 깨진 곳 0으로 변경
					count++; // 깨진 벽돌 수 증가
				}
			}
		}

		// 벽돌 아래로 내리기 -> stack을 사용하여 하나하나 내려줌
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (tmpMap[i][j] != 0) {
					stack.add(tmpMap[i][j]);
					tmpMap[i][j] = 0;
				}
			}

			int index = H - 1;
			while (!stack.isEmpty()) {
				tmpMap[index--][j] = stack.pop();
			}
		}

		// 모든 것을 완료한 tmpMap을 return
		return tmpMap;
	}

}