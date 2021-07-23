import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* (��)
 * <���� ���>
 * ���� ���� : �峭�� ȯ��ǰ�� �����ϱ� ���Ͽ� �ʿ��� �⺻ ��ǰ�� ������ ���� ���
 * ���� ���� : ���� ����
 * 
 * <Ǯ�̹� ���>
 * 1. �Է¹��� �� �߰� ��ǰ�� üũ���ش�
 * 2. ���� ������ �����ϸ鼭 ��ǰ�� ������ ������ش�.
 * 2-1. ���� ���� ��ǰ�� ó�� ����Ѵٸ� -> �ʿ� ��ǰ�� �ʿ� ������ �����ش�.
 * 2-2. �̹� ����� �� �ִ� ��ǰ�̶�� -> �ʿ� ��ǰ�� (���� ��ǰ�� ���� * �ʿ� ��ǰ)�� ������ �����ش�.
 * 3. 1���� ���� üũ�� ����Ͽ� �⺻ ��ǰ�� ��ȣ�� ������ ����Ѵ�.
 * 
 */

public class BOJ_G2_2637_�峭��_���� {

	static class Toy {
		int index;
		int cnt;

		public Toy(int index, int cnt) {
			this.index = index;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static int[] indegree, answer;
	// �߰� ��ǰ üũ
	// true : �߰� ��ǰ
	// false : �⺻ ��ǰ
	static boolean[] check;
	static ArrayList<ArrayList<Toy>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		indegree = new int[N + 1];
		answer = new int[N + 1];
		check = new boolean[N + 1];
		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			// X -> Y
			graph.get(X).add(new Toy(Y, K));
			// ���� ������ 1 ����
			indegree[Y]++;
			check[X] = true; // �߰� ��ǰ üũ
		}

		System.out.println(solve());
	}

	private static String solve() {
		topologySort();

		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			if (!check[i]) { // �⺻ ��ǰ�� ��
				sb.append(i).append(" ").append(answer[i]).append("\n");
			}
		}

		return sb.toString();
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();

		// ó�� ������ �� ���������� 0�� ��带 ť�� ����
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			for (Toy next : graph.get(now)) {
				// �ش� ���ҿ� ����� ������ ������������ 1 ����
				indegree[next.index]--;
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 ť�� ����
				if (indegree[next.index] == 0) {
					q.offer(next.index);
				}
				if (answer[now] == 0) { // ��ǰ���� ó�� ����Ѵٸ�
					answer[next.index] += next.cnt;
				} else { // �̹� ����� ���� �ִٸ�
					answer[next.index] += (answer[now] * next.cnt);
				}
			}
		}
	}

}
