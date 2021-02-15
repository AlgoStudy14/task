package Greedy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/***
 * <문제 요약>
 * 구해야 하는 것 : 필요한 최소 구명 보트의 개수.
 * 문제 유형 : 그리디.
 * 요구 개념 : 정렬, 덱.
 * <풀이법 요약>
 * 그리디 : 큰 사람을 한계까지 넣고, 작은 사람을 한계까지 넣는다.
 * 이유 : 큰 사람부터 넣거나, 작은 사람부터 넣는 경우 큰 사람이 타고 남은 자리의 공간 손실이 발생한다.
 * 1. 배열을 정렬하고, 덱에 차례로 넣는다.
 * 2. 큰 사람을 한계까지 넣고, 작은 사람을 한계까지 넣은뒤 보트 숫자 + 1.
 * 3. 덱이 다 비워지면 결과를 출력한다.
 */

public class 구명보트 {
	public static void main(String[] args) {
		구명보트 doit = new 구명보트();
		int[] people = new int[] { 70, 50, 80, 50 };
		int limit = 100;
		doit.solution(people, limit);
	}

	public int solution(int[] people, int limit) {
		int answer = 0;
		// 배열 정렬(오름 차순)
		Arrays.sort(people);
		// 덱에 사람들 넣기
		Deque<Integer> d = new ArrayDeque<>();
		for (int i = 0; i < people.length; i++) {
			d.offer(people[i]);
		}

		// 덱이 비워질때 까지 반복한다.
		while (!d.isEmpty()) {
			// 현재 무게
			int cur_weight = 0;
			// 큰 사람을 한계까지 넣는다.
			while (!d.isEmpty() && cur_weight + d.peekLast() <= limit) {
				cur_weight += d.pollLast();
			}
			// 작은 사람을 한계까지 넣는다.
			while (!d.isEmpty() && cur_weight + d.peek() <= limit) {
				cur_weight += d.poll();
			}
			// 보트 숫자 + 1.
			answer++;
		}

		return answer;
	}
}
