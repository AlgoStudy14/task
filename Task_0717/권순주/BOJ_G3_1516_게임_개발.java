import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * <���� ���>
 * ���� ���� : N���� �� �ǹ��� �ϼ��Ǳ���� �ɸ��� �ּ� �ð�
 * ���� ���� : ���� ����	
 * 
 */

public class BOJ_G3_1516_����_���� {

	static int N;
	static int[] indegree, timeTable, answer;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		indegree = new int[N + 1];
		timeTable = new int[N + 1];
		answer = new int[N + 1];

		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// ���� �׷����� ��� ���� ������ �Է� �ޱ�
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			timeTable[i] = time; // �ð� ����
			answer[i] = time; // ������ ���� �ð� ����
			while (true) {
				int node = Integer.parseInt(st.nextToken());
				// -1�� ������ �ݺ�
				if (node == -1) {
					break;
				}
				// node -> 1
				graph.get(node).add(i);
				// ���� ������ 1 ����
				indegree[i]++;
			}
		}

		topologySort();
	}

	// ���� ���� �Լ�
	static void topologySort() {
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
			// �ش� ���ҿ� ����� ������ ������������ 1 ����
			for (int i = 0; i < graph.get(now).size(); i++) {
				int node = graph.get(now).get(i);
				indegree[node]--;
				// �ڽ��� �ǹ��� ������, ������ ���� ���� �ð� �ɸ� ���� ã��
				answer[node] = Math.max(answer[node], answer[now] + timeTable[node]);
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 ť�� ����
				if (indegree[node] == 0) {
					queue.offer(node);
				}
			}
		}

		// ���� ���� ��� ���
		for (int i = 1; i <= N; i++) {
			System.out.println(answer[i]);
		}
	}

}
