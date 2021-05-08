import java.util.Arrays;

public class PM_L3_입국심사 {
	/*
	 * <입국 심사>
	 * 입국심사를 기다리는 사람 수 : n. 
	 * 심사관 당 걸리는 시간은 배열로 : times 에 저장. 
	 * 심사관 당 한번에 1명만 배정 가능.
	 * 가장 앞에 서있는 사람이 비어있는 곳으로 가서 심사 받을 수 있음.
	 * 	★더 빨리 끝나는 곳이 있다면 그곳으로 가서 심사 받기 가능.★
	 * 
	 * <제한>		
	 * 1 <= n <= 1,000,000,000				
	 * 1 <= times 값 <= 1,000,000,000		=> long 형으로.
	 * 1 <= times.length <= 100,000
	 * 		//이분탐색 문제는 제한사항에 주어지는 숫자가 굉장히 크고 최댓값 또는 최솟값을 구하는 경우가 많다. <-
	 * 
	 * <풀이>
	 * 이분 탐색할 것: 심사를 받는데 걸리는 시간
	 * 비교대상 : n
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println(solution(6, new int[] {7, 10}));
	}
	
	static long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        long start = 0, end = Long.MAX_VALUE, mid = 0, sum = 0;
        Arrays.sort(times);
        //mid = 심사를 받는데 걸리는 시간. mid 시간 동안 검사할 수 있는 사람 수를 구해야 함.
        while(start <= end) {
        	mid = (start + end) / 2;
        	sum = 0;
        	//mid 타임 동안 몇 명을 검사할 수 있는지 : sum
        	for(int i = 0; i < times.length; i++) {
        		sum += mid / times[i];
        		if (sum >= n)
        			break;
        	}
        	// 검사 받은 인원이 총원보다 적으면 다음 기준으로 이분탐색 while문 반복.
        	if(sum < n)
        		start = mid + 1;
        	// 검사를 전부 받았다면 최소값 갱신.
        	else {
        		end = mid - 1;
        		answer = Math.min(answer, mid);
        	}
        }
        return answer;
    }
}
