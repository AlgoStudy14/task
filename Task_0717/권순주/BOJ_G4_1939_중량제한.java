import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : �ѹ��� �̵��ؼ� �ű� �� �ִ� �߷��� �ִ�
 * ���� ���� : �̺� Ž�� + bfs
 * 
 * <�ǵ��>
 * �̺� Ž���� bfs�� ���� ����ؼ� �ű��ߴ�. ���� �����ε�!!
 * 
 */

public class BOJ_G4_1939_�߷����� {

	static class Node {
		int index;
		int weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}

	static int N, M, start, end, maxWeight;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		// �Է�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Node(B, C));
			graph.get(B).add(new Node(A, C));
			// �ְ� ���԰� üũ
			maxWeight = Math.max(maxWeight, C);
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		solve();
	}

	// �̺� Ž��
	private static void solve() {
		int answer = 0;
		int left = 1;
		int right = maxWeight;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (bfs(mid)) { // �ǳ� �� �ִٸ�
				answer = mid; // �� ����
				left = mid + 1; // �߷� ����
			} else { // �ǳ� �� ���ٸ�
				right = mid - 1; // �߷� ����
			}
		}

		System.out.println(answer);
	}

	// Ȯ��
	private static boolean bfs(int mid) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N + 1];
		queue.offer(start); // ������ �߰�
		visited[start] = true; // �湮 üũ

		while (!queue.isEmpty()) {
			int now = queue.poll();
			// �ǳ� �� �ִٸ� true return
			if (now == end) {
				return true;
			}
			// �׷��� Ž��
			for (int i = 0; i < graph.get(now).size(); i++) {
				// ���� node
				Node next = graph.get(now).get(i);
				// ���� ��尡 �湮 �����ϰ� + ��ǰ�� �ű� �� ���� �� (�ٸ��� ���԰� mid���� ũ�ų� ���� ��)
				if (!visited[next.index] && mid <= next.weight) {
					// �湮 üũ
					visited[next.index] = true;
					// ������ node �־��ֱ�
					queue.offer(next.index);
				}
			}
		}
		
		// ���� end�� ���� ���ߴٸ� �Ұ��� �ϱ� ������ false�� return �Ѵ�
		return false;
	}

}
