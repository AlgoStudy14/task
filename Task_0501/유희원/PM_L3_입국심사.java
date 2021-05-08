package algo0501;

import java.util.Arrays;
/*
 * n: 입국심사를 위해 줄서서 기다리는 사람 수
 * times: 각 심사관들이 한명을 심사하는데 걸리는 시간
 * 모든 사람이 심사를 받는데 걸리는 시간 최솟값 return
 */
public class PM_L3_입국심사 {

	public static void main(String[] args) {
		System.out.println(solution(6, new int[] {7,10}));
	}
	
	public static long solution(int n, int[] times) {
		Arrays.sort(times);
		long min = 1; //최적: 1초
		//여기에 Long.MAX_VALUE -1 하면 6,9번 틀림
		long max = (long)times[times.length-1]*n; //최악
		long mid = 0;
		long sum;
        long answer = max;
        //등호 주의!!!!!!!!!!!! 이거 빼먹어서 테케 6 틀림
        while(min<=max) {
        	sum = 0;
        	mid = (min+max)/2;
        	//심사관당 맡을 수 있는 입국자 수
        	for(int time:times) sum+=mid/time;
        	if(sum>=n) { //더 맡을 수 있으니까 시간 줄이고
        		if(mid<answer) answer = mid;
        		max = mid-1;
        	}else min = mid+1; //반대의 경우 시간 늘림
        }
        return answer;
    }

}
