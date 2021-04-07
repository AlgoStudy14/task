import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * <문제 요약>
 * 2차원 배열로 RGB 구역이 주어짐
 * 일반인인 경우와 적녹색약인 사람이 각각 구역을 볼 때 나누어지는 구역의 수 구하기
 * 
 * <풀이법 요약>
 * 일반인용 map과 적녹색약인 사람용 RGmap을 각각 만들어서 bfs로 구역 구했음
 */

public class Main_BOJ_10026_적록색약 {
	static int N;
	static char[][] map;
	static char[][] RGmap;
	static boolean[][] visited;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int count;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		RGmap = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'G') {
					RGmap[i][j] = 'R';
				} else {
					RGmap[i][j] = map[i][j];
				}
			}
		}

		visited = new boolean[N][N];
		count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					count++;
					findAns(map, i, j);
				}
			}
		}
		System.out.print(count + " ");

		visited = new boolean[N][N];
		count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					count++;
					findAns(RGmap, i, j);
				}
			}
		}
		System.out.print(count);
	}

	private static void findAns(char[][] map2, int r, int c) {
		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			if (check(r + dr[i], c + dc[i])) {
				if (visited[r + dr[i]][c + dc[i]] == false && map2[r + dr[i]][c + dc[i]] == map2[r][c])
					findAns(map2, r + dr[i], c + dc[i]);
			}
		}

	}

	private static boolean check(int i, int j) {
		if (i < 0 || j < 0 || i >= N || j >= N)
			return false;
		return true;
	}
}
