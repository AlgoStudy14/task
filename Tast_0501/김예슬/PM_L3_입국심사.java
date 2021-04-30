package algo0429;

import java.util.Arrays;

/***
 * <문제 요약>
 * 구해야 하는 것 : 모든 사람이 입국 심사를 받는데 걸리는 시간의 최솟값을 출력
 * 제약 사항 : n <= 10억, time <= 10억, 심사관 <= 10만
 * 문제 유형 : 이분탐색 사용
 * <풀이법 요약>
 * 1. 최대로 걸리는 시간을 기준으로 이분탐색
 * 2. 특정 시간 동안 심사할 수 있는 사람의 수 계산
 *  2-1. 시간이 더 필요할 경우, mid보다 큰 값부터 다시 탐색
 *  2-2. 시간이 충분할 경우, mid보다 작은 값까지 다시 탐색
 * 
 * c.f) 최대로 걸리는 시간을 구할 때 오버플로우 발생 -> long으로 형변환 필요..
 */

public class PM_L3_입국심사 {

	public static void main(String[] args) {
		System.out.println(solution(6, new int[]{7, 10}));
	}
	
    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long l = 1;
        long h = (long) n * times[times.length - 1];
        long answer = h;
        
        // 시간을 기준으로 이분탐색
        while (l <= h) {
        	long mid = (l + h) / 2;
        	long able = 0;
        	
        	// mid시간 동안 심사할 수 있는 사람의 수 계산
        	for (int time : times) {
        		able += mid / time;
        	}
        
        	if (able < n)			// 시간이 더 필요할 경우 
        		l = mid + 1;
        	else {					// 시간이 충분할 경우
        		h = mid - 1;
        		if (answer > mid)	// 시간이 충분할 경우, 더 최소인 값을 찾는 조건
        			answer = mid;
        	}
        }
        
        return answer;
    }
}
