import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * <���� ���>
 * ���� ���� : P�� S�� �κ� ���ڿ����� �ƴ��� Ȯ��
 * ���� ���� : kmp
 * 
 * <�ǵ��>
 * kmp...����...�ؾ߰ڴ�...
 * 
 */

public class BOJ_G4_16916_�κ�_���ڿ� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		System.out.println(kmp(S, P));
	}

	private static int kmp(String parent, String pattern) {
		int[] table = makeTable(pattern);
		char[] parents = parent.toCharArray();
		char[] patterns = pattern.toCharArray();
		int j = 0;
		for (int i = 0; i < parents.length; i++) {
			while (j > 0 && parents[i] != patterns[j]) {
				j = table[j - 1];
			}
			if (parents[i] == patterns[j]) {
				if (j == patterns.length - 1) {
					return 1;
				} else {
					j++;
				}
			}
		}
		return 0;
	}

	private static int[] makeTable(String pattern) {
		char[] patterns = pattern.toCharArray();
		int[] table = new int[patterns.length];
		int j = 0;
		for (int i = 1; i < patterns.length; i++) {
			while (j > 0 && patterns[i] != patterns[j]) {
				j = table[j - 1];
			}
			if (patterns[i] == patterns[j]) {
				table[i] = ++j;
			}
		}
		return table;
	}

}
