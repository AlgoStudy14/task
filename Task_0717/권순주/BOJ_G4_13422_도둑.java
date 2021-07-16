import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* (��)
 * <���� ���>
 * ���� ���� : ������ �������� �ʰ� ������ ������ ���������� ���� ����� �� 
 * ���� ���� : �����̵� ������
 * ���� ���� : ������ ���̱� ������ right ó���� right % N���� ����� �Ѵ�
 * 
 * <�ǵ��>
 * �� ������, ������, �����̵� ������ Ǯ�̹��� �ܼ��ϴ�! ��︸ �� �ص���!!
 * 
 */

public class BOJ_G4_13422_���� {

	static int T, N, M, K;
	static int[] house;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			house = new int[N];
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				house[i] = Integer.parseInt(st.nextToken());
				if (i < M) {
					sum += house[i]; // ó�� M���� �� ���ϱ�
				}
			}

			int left = 0;
			int right = M;

			if (N == M) { // ���� ���� = ���ľ� �� ���� ����
				if (sum < K) { // ���� K���� �۴ٸ� => 1��
					System.out.println(1);
				} else { // ���� K���� ũ�ų� ���ٸ� => 0��
					System.out.println(0);
				}
			} else {
				int answer = 0;
				// �����̵� ������
				while (left < N) { 
					if (sum < K) { // sum�� K���� ���� ���� ����
						answer++;
					}
					sum -= house[left]; // ���� ���ʲ� ���ֱ�
					sum += house[right % N]; // ���� �����ʲ� �����ֱ� + ������ ������ 0���� ����! => mod ���
					left++; // ���� ����
					right++; // ������ ����
				}
				System.out.println(answer);
			}
		}
	}
}
