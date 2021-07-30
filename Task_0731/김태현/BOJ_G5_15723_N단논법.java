package ss;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 연역적 명제가 주어질 때, 주어진 명제의 참 거짓을 판별하라(N단 논법 따지기).
 * 문제 유형 : 그래프, 구현.
 * <풀이 요약>
 * 1. 알파벳의 크기(26)만큼의 배열을 만든다.
 * 2. a = 0, b = 1...로 대응시키고, 주어진 값을 인접리스트로 만든다.
 * 3. 주어진 M개의 논제를 그래프를 탐색하며 참/거짓을 판단한다.
 * -> 만일, 목표 정점에 도달한 경우 T.
 * -> 정점에 도달하지 못하고 그래프 탐색이 종료된 경우 F.
 * <피드백>
 * 1. 작은 따옴표(')는 char를 의미하고, 큰 따옴표(")는 String을 의미한다.
 * 2. char형과 int형을 계산하면 숫자로 변환된다!(형변환)
 */

public class BOJ_G5_15723_N단논법 {
	static int n, m;
	static int[] alpha;

	public static void main(String[] args) {
		System.out.println('a');
		Scanner sc = new Scanner(System.in);
		// 다음 값을 보유하지 않은 대상은 -1.
		alpha = new int[26];
		for (int i = 0; i < 26; i++) {
			alpha[i] = -1;
		}

		n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int a = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';

			alpha[a] = b;
		}

		m = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int a = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int b = st.nextToken().charAt(0) - 'a';

			while (true) {
				if (alpha[a] == -1) {
					System.out.println("F");
					break;
				}
				if (alpha[a] == b) {
					System.out.println("T");
					break;
				} else {
					a = alpha[a];
				}
			}
		}
		sc.close();
	}
}
