package BruteForce;

import java.util.ArrayList;

/***
 * <문제 요약>
 * 구해야 하는 것 : 주어진 종이 조각을 붙여 만들 수 있는 소수의 경우의 수.
 * 문제 유형 : 완전 탐색
 * 요구 개념 : 소수 판단 알고리즘, 순열
 * <풀이법 요약>
 * 1. 주어진 String을 나눠 int배열에 각각 저장한다(target 배열).
 * 2. target 배열에 대하여 1 ~ String의 길이 까지 모든 순열을 구하며 다음을 진행한다.
 * -> 이 숫자가 소수 이면 answer++.
 */

public class 소수찾기 {
	public static void main(String[] args) {
		소수찾기 doit = new 소수찾기();
		String numbers = "17";
		doit.solution(numbers);
	}

	// target 배열, 방문 배열
	int[] target;
	boolean[] visited;
	// 순열의 길이
	int M;
	// 각 순열의 결과 숫자를 저장할 배열
	int[] result;
	// 결과
	int answer = 0;
	// 중복되는 소수를 판별하기 위한 리스트
	ArrayList<Integer> overlap = new ArrayList<Integer>();

	public int solution(String numbers) {
		// 1. 주어진 String을 나눠 int 배열에 각각 저장한다(target 배열).
		target = new int[numbers.length()];
		visited = new boolean[numbers.length()];
		for (int i = 0; i < numbers.length(); i++) {
			// char형을 int형으로 바꾸는 기법.
			target[i] = numbers.charAt(i) - '0';
		}

		// 2. target 배열에 대하여 1 ~ String의 길이까지 모든 순열에 대하여
		for (int i = 1; i <= numbers.length(); i++) {
			M = i;
			result = new int[M];
			perm(0);
		}
		System.out.println(answer);
		return answer;
	}

	public void perm(int cnt) {
		if (cnt == M) {
			String temp = "";
			for (int i = 0; i < result.length; i++) {
				temp += result[i];
			}
			// 소수 판별
			isPrime(temp);
			return;
		}
		for (int i = 0; i < target.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[cnt] = target[i];
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}

	// 소수 판별 알고리즘은 시간 복잡도를 고려한 심화된 버전이 있다! 여기서는 기본 버전만 이용.
	private void isPrime(String temp) {
		int a = Integer.parseInt(temp);
		// 검증 하는 숫자 중복 제거.
		if (overlap.contains(a)) {
			return;
		} else {
			overlap.add(a);
		}
		int cnt = 0;
		for (int i = 1; i <= a; i++) {
			if (a % i == 0) {
				cnt++;
			}
			// 공약수가 3개 이상이면 소수가 아님(함수 종료).
			if (cnt > 2) {
				return;
			}
		}
		// 판별이 끝났는데 공약수가 2개가 아니면 소수가 아님(함수 종료).
		if (cnt != 2) {
			return;
		}
		// 여기까지 왔으면 소수임.
		answer++;
	}
}
