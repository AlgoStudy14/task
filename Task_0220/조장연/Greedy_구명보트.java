import java.util.Arrays;

/*
 * <문제 요약>
 * 사람들의 몸무게 배열과 구명보트의 무게 제한이 주어졌을 때 가장 적은 구명보트 수 구하기
 * (보트에는 최대 2명이 들어갈 수 있음)
 * 
 * <풀이법 요약>
 * 몸무게배열을 sorting
 * 가장 몸무게 많이나가는 사람이랑 가장 적게나가는 사람이랑 태워봄(이때 안되면 무조건 무거운사람 혼자 타야함)
 */

public class Greedy_구명보트 {

	public static void main(String[] args) {
		Greedy_구명보트 doit = new Greedy_구명보트();
		int[] people = new int[] { 70, 50, 80, 50 };
		int limit = 100;
		System.out.println(doit.solution(people, limit));
	}

	public int solution(int[] people, int limit) {
		int answer = 0;
		int num = 0; // 기본 1인 1보트라고 생각하고 나중에 필요없는 보트만큼 빼주기 위해서
		int n = people.length; 

		Arrays.sort(people);

		for (int i = 0; i < people.length; i++) {
			for (int j = n - 1; j >= 0; j--) {
				if (i >= j) {
					answer = people.length - num;
					return answer;
				}

				else {
					if (people[i] + people[j] <= limit) {
						num++;
						n = j;
						break;
					}
				}
			}
		}

		answer = people.length - num;
		return answer;
	}
}
