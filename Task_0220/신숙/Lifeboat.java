package Greedy;

import java.util.Arrays;

public class Lifeboat {
	/*
	 * 무게제한이 있는 보트. 무게제한은 사람 몸무게보다 무조건 큼.
	 * 구명보트 개수 최솟값을 return.
	 * 
	 * 1. 먼저 오름차순 정렬
	 * 2. 반복문으로 people길이만큼 하는데, 몸무게를 더했을때 limit이 넘으면 초기화하고 다시 넣어줌.
	 * 3. limit이 안넘은 인원들을 위한 answer+1로 마지막.
	 * 
	 * => 2,8,14,15만 통과...?
	 * 제한조건 : 보트는 최대 2명....	=> 이러면 가장 적게 보트 쓰는건 최대한 가장 몸무게 적은사람과 많은사람 보내야겟네.
	 * 
	 * best : 몸무게 가장 많은 사람 + 몸무게 가장 적은사람  < limit.
	 * least : best는 안되고, 몸무게 가장 적은사람 + 그다음 적은사람 < limit
	 * => 가장 적은 사람은 항상 보내야 하고, 몸무게 많은 사람을 변경해야함. + 한명만 갈때는 가장 적은 사람을 보내기보다 가장 많은 사람을 먼저 보내면?
	 * + 한명만 가더라도 보트는 추가해야함.
	 */
	public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int min = 0;
        for(int i = people.length - 1; i >= min; i--) {
        	if(people[i] + people[min] <= limit)
        		min++;
        	answer++;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int[] peo = {70,50,60,80,50};
		int ans = solution(peo, 100);
		System.out.println(ans);
	}
}