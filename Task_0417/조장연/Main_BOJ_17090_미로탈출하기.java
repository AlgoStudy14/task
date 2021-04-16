import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17090_미로탈출하기 {
	static int R, C;
	static char[][] map;
	static int[][] answerMap;
	static boolean[][] visited;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		answerMap = new int[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (answerMap[i][j] == 1) {
					answer++;
					continue;
				}

				findRoot(i, j);
			}
		}
		System.out.println(answer);
	}

	private static boolean findRoot(int i, int j) {
		boolean flag = false;

		if (map[i][j] == 'U') {
			if (i - 1 < 0 || answerMap[i - 1][j] == 1) // 탈출
			{
				answer++;
				answerMap[i][j] = 1;
				return true;
			}

			if (answerMap[i - 1][j] == -1 || visited[i - 1][j] == true)
				return false;

			visited[i][j] = true;
			flag = findRoot(i - 1, j);
		} else if (map[i][j] == 'R') {
			if (j + 1 >= C || answerMap[i][j + 1] == 1) // 탈출
			{
				answer++;
				answerMap[i][j] = 1;
				return true;
			}

			if (answerMap[i][j + 1] == -1 || visited[i][j + 1] == true)
				return false;

			visited[i][j] = true;
			flag = findRoot(i, j + 1);
		} else if (map[i][j] == 'D') {
			if (i + 1 >= R || answerMap[i + 1][j] == 1) // 탈출
			{
				answer++;
				answerMap[i][j] = 1;
				return true;
			}

			if (answerMap[i + 1][j] == -1 || visited[i + 1][j] == true)
				return false;

			visited[i][j] = true;
			flag = findRoot(i + 1, j);
		} else if (map[i][j] == 'L') {
			if (j - 1 < 0 || answerMap[i][j - 1] == 1) // 탈출
			{
				answer++;
				answerMap[i][j] = 1;
				return true;
			}

			if (answerMap[i][j - 1] == -1 || visited[i][j - 1] == true)
				return false;

			visited[i][j] = true;
			flag = findRoot(i, j - 1);
		}

		if (flag == true) {
			answerMap[i][j] = 1;
			visited[i][j] = false;
			return true;
		}

		answerMap[i][j] = -1;
		visited[i][j] = false;
		return false;
	}

}
