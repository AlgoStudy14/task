import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : �л����� Ű�� ���� ����� ������ �� �����
 * ���� ���� : ���� ����
 * 
 * <�ǵ��>
 * �˰��� ������ ���� ����ΰ�..
 * 
 */

public class BOJ_G2_2252_��_����� {

	// N : ����� ����
	// M : ������ ����
	static int N, M;
	// �� ��忡 ���� ��������
	static int[] indegree;
	// �� ��忡 ����� ���� ������ ��� ���� ���� ����Ʈ �ʱ�ȭ
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N + 1];

		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// ���� �׷����� ��� ���� ������ �Է� �ޱ�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// A -> B
			graph.get(A).add(B);
			// ���� ������ 1 ����
			indegree[B]++;
		}

		topologySort();
	}

	// ���� ���� �Լ�
	static void topologySort() {
		// ���� ����� ���� ����Ʈ
		ArrayList<Integer> answer = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		// ó�� ������ �� ���������� 0�� ��带 ť�� ����
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		// ť�� �� ������ �ݺ�
		while (!queue.isEmpty()) {
			// ť���� ���� ������
			int now = queue.poll();
			answer.add(now);
			// �ش� ���ҿ� ����� ������ ������������ 1 ����
			for (int i = 0; i < graph.get(now).size(); i++) {
				int node = graph.get(now).get(i);
				indegree[node]--;
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 ť�� ����
				if (indegree[node] == 0) {
					queue.offer(node);
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		// ���� ���� ��� ���
		for (Integer node : answer) {
			sb.append(node).append(" ");
		}
		System.out.println(sb.toString());
	}

}
