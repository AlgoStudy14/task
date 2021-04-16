import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1504_특정한최단경로 {
	static int N, M, node1, node2;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					map[i][j] = 0;
				else
					map[i][j] = 100000000;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sp = Integer.parseInt(st.nextToken()) - 1;
			int ep = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			map[sp][ep] = weight;
			map[ep][sp] = weight;
		}

		st = new StringTokenizer(br.readLine());
		node1 = Integer.parseInt(st.nextToken()) - 1;
		node2 = Integer.parseInt(st.nextToken()) - 1;

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}

		if ((map[0][node1] == 100000000 && map[0][node2] == 100000000)
				|| (map[N - 1][node1] == 100000000 && map[N - 1][node2] == 100000000)
				|| map[node1][node2] == 100000000) {
			System.out.println("-1");
			return;
		}

		if (map[0][node1] + map[N - 1][node2] > map[0][node2] + map[N - 1][node1]) {
			System.out.println(map[0][node2] + map[N - 1][node1] + map[node1][node2]);
		} else
			System.out.println(map[0][node1] + map[N - 1][node2] + map[node1][node2]);
	}

}
