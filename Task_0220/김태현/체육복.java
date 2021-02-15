package Greedy;


/***
 * <문제 요약>
 * 구해야 하는 것 : 체육복을 가질 수 있는 학생 수(체육 수업을 들을 수 있는 학생 수).
 * 문제 유형 : 구현, 그리디.
 * 요구 개념 : 리스트, 반복문
 * <풀이법 요약>
 * 1. 전체 학생을 담을 배열을 만들고 true로 초기화(이때, 리스트를 +1의 크기로 만들어 <인덱스 = 번호>를  적용할 수 있는 스킬 사용).
 * 2. 체육복을 잃은 학생 인덱스를 false로 만든다.
 * 3. 여벌 배열을 탐색하며 정확한 기준을 바탕으로 체육복을 빌려준다(그리디)
 * -> 자기 자신이 잃어버린 경우를 먼저 제외 한다(옆 사람이 자신에게 빌려주는 경우 제거).
 * -> 탐색하는 방향이 (->)인 경우, 앞 사람, 뒷 사람 순서로 체육복을 빌려준다
 * -> 탐색하는 방향이 (<-)인 경우, 뒷 사람, 앞 사람 순서로 체육복을 빌려준다
 * 4. 전체 배열을 탐색하며 1의 개수를 카운팅한다.
 */


public class 체육복 {
	public static void main(String[] args) {
		체육복 doit = new 체육복();
		int n = 5;
		int[] lost = new int[] { 2, 4 };
		int[] reserve = new int[] { 1, 3, 5 };
		doit.solution(n, lost, reserve);
	}

	public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;

		// 1. 전체 학생 배열 초기화.
		boolean[] student = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			student[i] = true;
		}

		// 2. 체육복을 잃은 학생 인덱스 초기화.
		for (int i = 0; i < lost.length; i++) {
			student[lost[i]] = false;
		}

		// 3. 체육복을 빌려준다(자신, 앞, 뒷 사람 순서)
		for (int i = 0; i < reserve.length; i++) {
			if (!student[reserve[i]]) {
				student[reserve[i]] = true;
				reserve[i] = 0;
			}
		}

		for (int i = 0; i < reserve.length; i++) {
			if (reserve[i] != 1 && reserve[i] != 0) {
				if (!student[reserve[i] - 1]) {
					student[reserve[i] - 1] = true;
					continue;
				}
			}
			if (reserve[i] != n && reserve[i] != 0) {
				if (!student[reserve[i] + 1]) {
					student[reserve[i] + 1] = true;
				}
			}
		}

		// 4. 전체 배열을 탐색하며 1의 개수를 카운팅한다.
		for (int i = 1; i <= n; i++) {
			if (student[i]) {
				answer++;
			}
		}

		return answer;
	}
}
