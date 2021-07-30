import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * <���� ����>
 * ���� ���� : ���ڿ� ��Ī ���ϱ�
 * ���� ���� : kmp
 * 
 */

public class BOJ_G1_1786_ã�� {

	static String T, P;
	static ArrayList<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		kmp(T, P);
		System.out.println(answer.size());
		for (Integer index : answer) {
			System.out.print(index + " ");
		}
		System.out.println();
	}

	// a b a c a a b a
	// 0 0 1 0 1 1 2 3
	
	// i : a b a b a c a b a c a a b a c a a b a
	// j : a b a c a a b a 
	//     a b a c a a b a 
	//         a b a c a a b a
	//                 a b a c a a b a
 	private static void kmp(String parent, String pattern) {
		// �ִ� ��ġ���� ���̺� -> �󸶳� ���� ���λ�� ���̻簡 ��ġ�ϴ���
		int[] table = makeTable(pattern);
		char[] parents = parent.toCharArray();
		char[] patterns = pattern.toCharArray();
		int j = 0;
		for (int i = 0; i < parents.length; i++) {
			// ��ġ���� �ʴ� ��찡 �߻����� �� -> �����ܰ��� ��ġ�� �̵�
			while (j > 0 && parents[i] != patterns[j]) {
				j = table[j - 1];
			}
			if (parents[i] == patterns[j]) {
				// ��� ���ڰ� ��ġ�� ���
				if (j == patterns.length - 1) {
					answer.add(i - patterns.length + 2);
					// j�� ����Ű�� ��ġ��ŭ ����
					j = table[j];
				} else {
					// ����ؼ� ��Ī�� �̷�������� Ȯ���� ���� ����
					j++;
				}
			}
		}

	}

	
	// ���λ�� ���̻簡 ��ġ�ϴ� �ִ� ����
	// 0. j=0, i=1 start
	// 1. i�� j�� ��ġ���� �ʴ� ��� -> i+1
	// 2. i�� j�� ��ġ�ϴ� ��� -> j+1, i+1 -> ��ġ�ϴ� j�� index+1�� table�� ����
	// 2-1. �̵��ߴµ� ��ġ���� �ʴ� ��� -> j�� ��ĭ �������� �̵���Ų��
	// a b a c a a b a b
	// 0 0 1 0 1 1 2 3 2
	private static int[] makeTable(String pattern) {
		char[] patterns = pattern.toCharArray();
		int[] table = new int[patterns.length];
		// 0. j=0, i=1 start
		int j = 0;
		// 1. i�� j�� ��ġ���� �ʴ� ��� -> i+1
		for (int i = 1; i < pattern.length(); i++) {
			// 2-1. �̵��ߴµ� ��ġ���� �ʴ� ��� -> j�� ��ĭ �������� �̵���Ų�� -> ��ġ�ϴ� ���λ� ã��
			while (j > 0 && patterns[i] != patterns[j]) {
				j = table[j - 1];
			}
			// 2. i�� j�� ��ġ�ϴ� ��� -> j+1, i+1 -> ��ġ�ϴ� j�� index+1�� table�� ����
			if (patterns[i] == patterns[j]) {
				table[i] = ++j;
			}
		}
		return table;
	}

}
