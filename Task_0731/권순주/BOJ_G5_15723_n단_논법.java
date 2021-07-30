import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * <���� ���>
 * ���� ���� : n���� ������ ���� ��, m���� ����� ���� ���Ѵ�
 * ���� ���� : �÷��̵� �ͼ�
 * ���� ���� : �ִ밪 : 25
 * 
 */

public class BOJ_G5_15723_n��_��� {

	static final int INF = 30;
	static int N, M;
	static int[][] graph = new int[26][26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// �ִ� �Ÿ� ���̺� �ʱ�ȭ
		for (int i = 0; i < 26; i++) {
			Arrays.fill(graph[i], INF);
		}

		// �ڱ� �ڽſ��� �ڱ� �ڽ����� ���� ��� 0���� �ʱ�ȭ
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}

		// �Է�
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			int start = line.charAt(0) - 'a';
			int end = line.charAt(line.length() - 1) - 'a';
			graph[start][end] = 1;
		}

		// �÷��̵� �ͼ� �˰��� ����
		for (int k = 0; k < 26; k++) {
			for (int a = 0; a < 26; a++) {
				for (int b = 0; b < 26; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}

		// ����� ���
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String line = br.readLine();
			int start = line.charAt(0) - 'a';
			int end = line.charAt(line.length() - 1) - 'a';
			if (graph[start][end] != INF) { // ���� �����ϸ� -> T
				System.out.println("T");
			} else { // ���� �������� ������ -> F
				System.out.println("F");
			}
		}
	}

}
