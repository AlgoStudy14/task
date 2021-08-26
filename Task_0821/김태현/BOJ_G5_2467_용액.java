package ss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (완)
 * <문제 요약>
 * 문제 정의 : 특성값이 0에 가장 가까워지는 두 용액을 구하여라.
 * 문제 유형 : 이분 탐색.
 * <풀이 요약>
 * 1. 두 용액의 양 끝 지점에 각각 포인터(인덱스)를 위치시킨다.
 * 2. 두 인덱스의 합을 구한다.
 * -> 합이 음수라면, 좌측 인덱스를 우측으로 이동시킨다.
 * -> 합이 양수라면, 우측 인덱스를 좌측으로 이동시킨다.
 * -> 인덱스가 겹치는 순간 종료한다.
 * -> 매 계산 마다 값을 갱신 시킨다. 
 */

public class BOJ_G5_2467_용액 {
	static int N, min;
	static int[] arr, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int a = 0, b = N - 1;
		min = Integer.MAX_VALUE;
		res = new int[2];
		while (a < b) {
			int sum = arr[a] + arr[b];
			int diff = Math.abs(sum);
			if (diff < min) {
				min = diff;
				res[0] = arr[a];
				res[1] = arr[b];
			}

			if (sum < 0) {
				a++;
			} else {
				b--;
			}
		}
		
		System.out.println(res[0] + " " + res[1]);
	}
}
