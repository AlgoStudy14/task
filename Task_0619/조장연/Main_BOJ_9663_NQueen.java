import java.util.Scanner;

public class Main_BOJ_9663_NQueen {
	static int N;
	static boolean[][] map;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];

		dfs(0);

		System.out.println(answer);

	}

	private static void dfs(int count) {
		if (count == N) {
			answer++;
			return;
		}

		for (int j = 0; j < N; j++) {
			if (map[count][j] != true && check(count, j) == true) {
				map[count][j] = true;
				dfs(count + 1);
				map[count][j] = false;
			}
		}

	}

	private static boolean check(int i, int j) {
		for (int a = 0; a < N; a++) { // 가로줄
			if (map[i][a] == true)
				return false;
			if (map[a][j] == true) // 세로줄
				return false;
		}

		int count = 1;
		while (i - count >= 0 && j - count >= 0) {
			if (map[i - count][j - count] == true)
				return false;
			else
				count++;
		}

		count = 1;
		while (i + count < N && j - count >= 0) {
			if (map[i + count][j - count] == true)
				return false;
			else
				count++;
		}

		count = 1;
		while (i - count >= 0 && j + count < N) {
			if (map[i - count][j + count] == true)
				return false;
			else
				count++;
		}

		count = 1;
		while (i + count < N && j + count < N) {
			if (map[i + count][j + count] == true)
				return false;
			else
				count++;
		}

		return true;
	}

}
