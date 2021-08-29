package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* (완)
 * <풀이 : 투 포인터>
 * 1. 주어진 개발자 능력치의 양 끝 지점에 포인터를 놓는다.
 * 2. 능력치를 계산하며 갱신한다.
 * -> 이때, 양 끝 지점에서 더 작은 지점의 포인터를 가운데로 옮긴다.
 * -> 포인터가 만나는 순간 반복을 종료한다.
 * <피드백>
 * 투 포인터인거 알고 문제 이해만 하면 뭐.. 어렵지 않은 문제!
 */

public class BOJ_G5_22945_팀빌딩 {
	static int N;
	static int[] devs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		devs = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			devs[i] = Integer.parseInt(st.nextToken());
		}

		int a = 0, b = N - 1;
		int max = 0;
		while (a < b) {
			int gap = b - a - 1;

			// 왼쪽이 더 큰 경우
			if (devs[a] >= devs[b]) {
				max = Math.max(max, devs[b--] * gap);
			}
			// 오른쪽이 더 큰 경우
			else {
				max = Math.max(max, devs[a++] * gap);
			}
		}
		System.out.println(max);
	}
}
