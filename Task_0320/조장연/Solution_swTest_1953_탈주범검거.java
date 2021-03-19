import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <문제 요약>
 * 지도와 타일의 모양, 맨홀뚜껑, 시간이 주어졌을 때 범인이 있을 수 있는 통로의 지점수 구하기
 * 
 * <풀이법 요약>
 * BFS 사용
 * 큐에서 뺀 타일의 모양에 따라 갈 수 있는 경로를 큐에 계속 집어넣는 형식.
 * 다풀고나서 코드를 보니까 각각의 타일에서 갈수 있는 경우가 중복됨을 알았음
 * 이걸 각각의 함수로 빼내서 switch문에서 그 함수를 썻으면 더 코드가 간결했을 것 같음
 */

public class Solution_swTest_1953_탈주범검거 {
	static int T, N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int answer;
	static Queue<int[]> queue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt() - 1;
			map = new int[N][M];
			visited = new boolean[N][M];
			answer = 1;

			for (int i = 0; i < N; i++) { // map 초기화
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			queue = new LinkedList<>();
			int[] start = { R, C };
			int[] end = { -1, -1 };
			queue.offer(start);
			queue.offer(end);
			visited[R][C] = true;

			for (int i = 0; i < L; i++) { // 시간에 따라
				while (true) {
					int[] position = queue.poll();
					if (position[0] == -1) {
						queue.offer(end);
						break;
					}

					addQueue(position[0], position[1]);
				}
			}
			System.out.println("#" + t + " " + answer);
		}
	}

	private static void addQueue(int r, int c) {
		int type = map[r][c];
		int[] arr1 = { r, c - 1 };
		int[] arr2 = { r - 1, c };
		int[] arr3 = { r, c + 1 };
		int[] arr4 = { r + 1, c };

		switch (type) {
		case 0:

			break;
		case 1:
			if (check(r, c - 1)
					&& (map[r][c - 1] == 1 || map[r][c - 1] == 3 || map[r][c - 1] == 4 || map[r][c - 1] == 5)
					&& visited[r][c - 1] == false) {

				queue.offer(arr1);
				visited[r][c - 1] = true;
				answer++;
			}
			if (check(r - 1, c)
					&& (map[r - 1][c] == 1 || map[r - 1][c] == 2 || map[r - 1][c] == 5 || map[r - 1][c] == 6)
					&& visited[r - 1][c] == false) {
				queue.offer(arr2);
				visited[r - 1][c] = true;
				answer++;
			}
			if (check(r, c + 1)
					&& (map[r][c + 1] == 1 || map[r][c + 1] == 3 || map[r][c + 1] == 6 || map[r][c + 1] == 7)
					&& visited[r][c + 1] == false) {
				queue.offer(arr3);
				visited[r][c + 1] = true;
				answer++;
			}
			if (check(r + 1, c)
					&& (map[r + 1][c] == 1 || map[r + 1][c] == 2 || map[r + 1][c] == 4 || map[r + 1][c] == 7)
					&& visited[r + 1][c] == false) {
				queue.offer(arr4);
				visited[r + 1][c] = true;
				answer++;
			}
			break;
		case 2:
			if (check(r - 1, c)
					&& (map[r - 1][c] == 1 || map[r - 1][c] == 2 || map[r - 1][c] == 5 || map[r - 1][c] == 6)
					&& visited[r - 1][c] == false) {
				queue.offer(arr2);
				visited[r - 1][c] = true;
				answer++;
			}
			if (check(r + 1, c)
					&& (map[r + 1][c] == 1 || map[r + 1][c] == 2 || map[r + 1][c] == 4 || map[r + 1][c] == 7)
					&& visited[r + 1][c] == false) {
				queue.offer(arr4);
				visited[r + 1][c] = true;
				answer++;
			}
			break;
		case 3:
			if (check(r, c - 1)
					&& (map[r][c - 1] == 1 || map[r][c - 1] == 3 || map[r][c - 1] == 4 || map[r][c - 1] == 5)
					&& visited[r][c - 1] == false) {
				queue.offer(arr1);
				visited[r][c - 1] = true;
				answer++;
			}
			if (check(r, c + 1)
					&& (map[r][c + 1] == 1 || map[r][c + 1] == 3 || map[r][c + 1] == 6 || map[r][c + 1] == 7)
					&& visited[r][c + 1] == false) {
				queue.offer(arr3);
				visited[r][c + 1] = true;
				answer++;
			}
			break;
		case 4:
			if (check(r - 1, c)
					&& (map[r - 1][c] == 1 || map[r - 1][c] == 2 || map[r - 1][c] == 5 || map[r - 1][c] == 6)
					&& visited[r - 1][c] == false) {
				queue.offer(arr2);
				visited[r - 1][c] = true;
				answer++;
			}
			if (check(r, c + 1)
					&& (map[r][c + 1] == 1 || map[r][c + 1] == 3 || map[r][c + 1] == 6 || map[r][c + 1] == 7)
					&& visited[r][c + 1] == false) {
				queue.offer(arr3);
				visited[r][c + 1] = true;
				answer++;
			}
			break;
		case 5:
			if (check(r + 1, c)
					&& (map[r + 1][c] == 1 || map[r + 1][c] == 2 || map[r + 1][c] == 4 || map[r + 1][c] == 7)
					&& visited[r + 1][c] == false) {
				queue.offer(arr4);
				visited[r + 1][c] = true;
				answer++;
			}
			if (check(r, c + 1)
					&& (map[r][c + 1] == 1 || map[r][c + 1] == 3 || map[r][c + 1] == 6 || map[r][c + 1] == 7)
					&& visited[r][c + 1] == false) {
				queue.offer(arr3);
				visited[r][c + 1] = true;
				answer++;
			}
			break;
		case 6:
			if (check(r + 1, c)
					&& (map[r + 1][c] == 1 || map[r + 1][c] == 2 || map[r + 1][c] == 4 || map[r + 1][c] == 7)
					&& visited[r + 1][c] == false) {
				queue.offer(arr4);
				visited[r + 1][c] = true;
				answer++;
			}
			if (check(r, c - 1)
					&& (map[r][c - 1] == 1 || map[r][c - 1] == 3 || map[r][c - 1] == 4 || map[r][c - 1] == 5)
					&& visited[r][c - 1] == false) {
				queue.offer(arr1);
				visited[r][c - 1] = true;
				answer++;
			}
			break;
		case 7:
			if (check(r - 1, c)
					&& (map[r - 1][c] == 1 || map[r - 1][c] == 2 || map[r - 1][c] == 5 || map[r - 1][c] == 6)
					&& visited[r - 1][c] == false) {
				queue.offer(arr2);
				visited[r - 1][c] = true;
				answer++;
			}
			if (check(r, c - 1)
					&& (map[r][c - 1] == 1 || map[r][c - 1] == 3 || map[r][c - 1] == 4 || map[r][c - 1] == 5)
					&& visited[r][c - 1] == false) {
				queue.offer(arr1);
				visited[r][c - 1] = true;
				answer++;
			}
			break;

		default:
			break;
		}

	}

	private static boolean check(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		return true;
	}

}
