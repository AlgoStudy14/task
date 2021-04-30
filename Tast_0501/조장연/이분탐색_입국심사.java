import java.util.*;

/*
 * <문제 요약>
 * 고객의 수와 각 심사관이 걸리는 시간이 주어졌을 때 최소로 걸리는 시간 구하기
 * 
 * <풀이법 요약>
 * 이분탐색 사용
 * 초기값은 가장 시간이 오래걸리는 심사관이 모든 사람을 처리할때의 시간(max값)
 * 시간을 기준으로 이분탐색을 사용.
 * 해당 시간에 심사관이 각자 처리할수 있는 사람의 합을 더해서 n과 비교
 * 만약 n이 더 큰경우 시간이 부족하다는 뜻
 * n이 작은 경우 시간이 넉넉하다는 뜻(시간을 더 줄여도 됨)
 */

class 입국탐색_입국심사 {
	public long solution(int n, int[] times) {
		Arrays.sort(times);
		long min = 1;
		long max = (long) times[times.length - 1] * n;
		long mid = 0;
		long answer = max;

		while (max >= min) {
			mid = (max + min) / 2;
			long num = 0;

			for (int time : times) {
				num += mid / time;
			}

			if (num >= n) {
				if (mid < answer)
					answer = mid;
				max = mid - 1;
			}

			else {
				min = mid + 1;
			}
		}

		return answer;
	}
}