import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * <���� ���>
 * ���� ���� : ���� 0�� ����� �� ����� ã��
 * ���� ���� : �� ������
 * ���� ���� : Max : 2000000000, �񱳸� ���� ���밪�� ����ؾ��Ѵ�
 * 
 * <Ǯ�̹� ���>
 * 1. �Է¹��� �迭�� �����Ѵ�.
 * 2. ���� ���� �� �����Ϳ� �ش��ϴ� ���� ���� 0�� ����������� �� �����͸� �����Ѵ�.
 * 2-1. ���� 0���� ���� �� : ���� 0���� �۱� ������ �� ���� ���� �� Ŀ���� 0�� ��������� -> left ����(�������� ����)
 * 2-2. ���� 0���� Ŭ �� : ���� 0���� ũ�� ������ �� ���� ���� �� �۾����� 0�� ��������� -> right ����(ū�� ����)
 * 3. ������ ������ �հ� ���Ͽ� ����� �����Ѵ� + ���밪�� ����Ͽ� ���Ѵ�.
 * 3-1. ���� 0�̶�� �ƹ����̳� ����ص� ������ ������ break���ش�.
 * 
 */

public class BOJ_G5_2470_��_��� {

	final static int INF = 2000000005;
	static int N;
	static int[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		// �迭 ����
		Arrays.sort(list);

		solve();
	}

	static void solve() {
		// �� ������ ����
		int left = 0;
		int right = N - 1;
		int[] answer = new int[2];
		int sumMin = INF;

		while (left < right) {
			int sum = list[left] + list[right];

			// ���� ���Ѵ� + �񱳸� ���� ���밪�� ���Ѵ�
			if (sumMin > Math.abs(sum)) {
				sumMin = Math.abs(sum);
				// answer�� �� ����� ũ�⸦ �����Ѵ�.
				answer[0] = list[left];
				answer[1] = list[right];
				
				// ���� sum�� 0�� �ȴٸ� ��
				if (sum == 0) {
					break;
				}
			}

			if (sum < 0) { // ���� 0���� �۱� ������ �� ���� ���� �� Ŀ���� 0�� ��������� -> left ����(�������� ����)
				left++;
			} else { // ���� 0���� ũ�� ������ �� ���� ���� �� �۾����� 0�� ��������� -> right ����(ū�� ����)
				right--;
			}
		}
		
		// ���
		for (int i = 0; i < 2; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}
