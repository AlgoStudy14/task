package s0703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 주어진 숫자로부터 만들 수 있는 모든 번호의 조합.
 * 문제 유형 : 조합.
 * <풀이 요약>
 * 1. 주어진 숫자가 0이 나올때 까지 조합을 이용하여 번호를 출력한다.
 */

public class BOJ_S2_6603_로또 {
	static int k;
	static int[] nums;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 0이 나오면 종료
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			nums = new int[k];
			result = new int[6];
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			Combination(0, 0);
			System.out.println();
		}
	}

	private static void Combination(int cnt, int idx) {
		if(cnt == 6) {
			for(int i = 0; i < 6; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = idx; i < k; i++) {
			result[cnt] = nums[i];
			Combination(cnt + 1, i + 1);
		}
	}
}
