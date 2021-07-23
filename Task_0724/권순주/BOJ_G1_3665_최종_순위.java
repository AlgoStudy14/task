import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : �۳� ������ ������� ������ �ٲ� ��� ���� ����� �־����� ��, ���� ������ ���Ͽ���
 * ���� ���� : ���� ����
 * ���� ���� : �� ���� ������ ��� �����Ѵ�
 * 
 * <�ǵ��>
 * ����� ���� ���۸�..
 * 
 */

public class BOJ_G1_3665_����_���� {

	static int T, N, M;
	static int[] indegree, lastRank;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			graph = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			// �ʱ�ȭ
			lastRank = new int[N];
			indegree = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				lastRank[i] = Integer.parseInt(st.nextToken());
			}

			// �۳� ������ �׷��� �ۼ� -> �� ���� ���� ��� ����
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					graph.get(lastRank[i]).add(lastRank[j]);
					indegree[lastRank[j]]++; // �������� ����
				}
			}

			// �׷��� ���� ������ ����
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (graph.get(a).contains(b)) {
					graph.get(a).remove(Integer.valueOf(b));
					graph.get(b).add(a);
					indegree[a]++;
					indegree[b]--;
				} else {
					graph.get(b).remove(Integer.valueOf(a));
					graph.get(a).add(b);
					indegree[b]++;
					indegree[a]--;
				}
			}
			
			System.out.println(topologySort());
		}
	}

	// ���� ���� �Լ�
	static String topologySort() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		// IMPOSSIBLE �Ǵ��� ���� ����
		int cnt = 0;
		// ó�� ������ �� ���������� 0�� ��带 ť�� ����
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
				cnt++;
			}
		}

		// ť�� �� ������ �ݺ�
		while (!queue.isEmpty()) {
			// q�� ����� 2�� ��, �� �������� ���� �� -> Ȯ���� ������ �� �� ���� ������ ?�� return
			if (queue.size() > 1) {
				return "?\n";
			}
			// ť���� ���� ������ ����
			int now = queue.poll();
			sb.append(now).append(" ");
			// �ش� ���ҿ� ����� ������ ������������ 1 ����
			for (Integer next : graph.get(now)) {
				indegree[next]--;
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 ť�� ����
				if (indegree[next] == 0) {
					queue.offer(next);
					cnt++;
				}
			}
		}

		// �湮�� ����� ���� N���� �ٸ��ٸ� ������ ���� �� ����
		if (N != cnt) {
			return "IMPOSSIBLE";
		} else {
			return sb.toString();
		}
	}

}
