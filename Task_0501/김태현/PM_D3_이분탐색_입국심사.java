package s0501;

import java.util.Arrays;

/*
 * <문제 요약>
 * 구해야 하는 것 : 모든 사람이 심사하는데 걸리는 시간의 최솟값.
 * 문제 유형: 이분 탐색.
 * <풀이법>
 * 1. 탐색의 기준을 '검사 당하는 사람의 수'로 정한다.
 * 2. 최대, 최소 시간을 설정하고 최적의 시간을 이분 탐색한다.
 * -> 각 시간별 검사하는 가능한 사람수를 바탕으로 이분 탐색.
 * <주의 사항>
 * 자바에서 이분 탐색을 특히 형변환을 매우 주의해주어야 한다.
 */
public class PM_D3_이분탐색_입국심사 {
	public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
		// 시간을 정렬하고, 최대 심사 시간 및 최소 심사 시간을 구한다.
		Arrays.sort(times);
		
		long l = 1;
		// 형변환(int형 연산이기 때문에, 한계를 만나면 오버플로우가 발생!!).
		long r = (long)times[times.length - 1] * n;
        long time = 0;
        long cnt = 0;
		while(l <= r) {
			time = (l + r) / 2;
			// 해당 시간에 몇명을 탐색 가능한 사람의 수가 목표 사람수와 같은지 확인.
			cnt = 0;
			for (int i = 0; i < times.length; i++) {
				cnt += (time / times[i]);
			}
			
			if(cnt >= n) {
				r = time - 1;
                answer = Math.min(answer, time);
			} else {
				l = time + 1;
            }
		}
        
		return answer;
	}
}