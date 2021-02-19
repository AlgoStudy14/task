import java.util.Arrays;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 최대 2명이 탈 수 있는 구명보트를 최소한으로 사용할 때, 구명보트의 수 
 * 요구 개념 : 그리디
 * 문제 핵심 요약 : 최대 2명이 탈 수 있는 것이 중요 -> 무거운 사람은 무조건 태움 -> 무거운 사람 기준으로 생각
 * <풀이법 요약> 
 * 문제의 핵심은 몸무게가 큰 사람을 기준으로 생각하는것 (최대 2명만 탈 수 있기 때문에)
 * 만약 가장 큰 몸무게 + 가장 작은 몸무게 > limit 이라면 가장 큰 몸무게는 무조건 혼자 가야한다.	
 * 1. 배열 정렬 (내림차순 정렬해도 되지만 귀찮아서 그냥 정렬함)
 * 2. 몸무게가 가장 작은 사람과 큰 사람의 index를 저장해서 접근
 * 3. (1) 만약 가장 큰 몸무게 + 가장 작은 몸무게 <=limit이라면 가장 작은 몸무게를 가진 사람도 탈 수 있기 때문에 증가
 * 3. (2) 가장 큰 몸무게를 가진 사람은 무조건 태움
 * 4. answer 카운팅 출력
 */

public class PM_그리디_구명보트 {

	public int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		
		int left = 0, right = people.length - 1; // 몸무게가 가장 큰 사람과 가장 작은 사람의 index를 저장

		while (left < right + 1) { // index의 차이가 1일 때까지 반복
			if (people[left] + people[right] <= limit) // 만약 가장 큰 몸무게 + 가장 작은 몸무게 <=limit이라면
				left++; // 몸무게가 가장 작은 사람 태움
			right--; // 몸무게가 가장 무거운 사람은 항상 태움
			answer++; // 카운팅
		}

		return answer;
	}

	public static void main(String[] args) {
		PM_그리디_구명보트 pm = new PM_그리디_구명보트();
		int[] people = { 70, 50, 80, 50 };
		int limit = 100;
		System.out.println(pm.solution(people, limit));
	}

}
