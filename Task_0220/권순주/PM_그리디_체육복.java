/***
 * <문제 요약> 
 * 구해야 하는 것 : 체육복을 입을 수 있는 최대한 많은 학생 구하기
 * 문제 유형 : 방법을 찾는 구현
 * 요구 개념 : 그리디
 * <풀이법 요약> 
 * 1. 일차원 배열에 각 학생의 체육복 상태를 저장해서 풀이 (-1 : 없음 / 0 : 한벌 있음 / 1 : 두벌 있음)
 * 2. 맨 처음 최대 학생수를 카운팅 하면서 시작 (전체 학생수 - 도난당한 학생수)
 * 3. 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 
 * 	    이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 * 	    위 조건을 만족시키기 위해 여벌을 가지고 있는 학생이 도난을 당한 학생인지 체크하는 부분이 필요
 * 4. (1) 이전학생이 존재 + 그 학생이 도난당한 학생이라면 -> 이전학생 +1 / 나 -1 / 카운팅
 * 	  (2) 다음학생이 존재 + 그 학생이 도난당한 학생이라면 -> 다음학생 +1 / 나 -1 / 카운팅
 * 5. 카운팅 한 수 return
 * 왜인지 모르겠지만 푸는데 더럽게 오래걸림... 
 */

public class PM_그리디_체육복 {

	// -1 : 없음 / 0 : 한벌 있음 / 1 : 두벌 있음
	public int solution(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length; // 우선 당장 체육복을 입을 수 있는 인원을 저장
		int[] student = new int[n + 1]; // index 접근 쉽게하기 위해서 n+1의 크기로 배열 생성

		for (int i = 0; i < lost.length; i++) { // 도난당한 학생들은 체육복 하나씩 빼주기
			student[lost[i]]--;
		}

		for (int i = 0; i < reserve.length; i++) { // 여별이 있는 학생들은 체육복 하나씩 증가
			student[reserve[i]]++;
			if (student[reserve[i]] == 0) { // 만약 도난을 당한사람이 여벌 체육복도 가지고 있다면 카운팅
				answer++;
			}
		}

		for (int i = 1; i <= n; i++) { // 전체 학생들을 돌면서
			if (student[i] == 1) { // 만약 여벌을 가지고 있는 학생이 있다면
				if ((i - 1) > 0 && student[i - 1] == -1) { // 이전 학생이 존재하고 + 그 학생이 도난당한 학생이라면
					student[i - 1]++; // 이전 학생 체육복수 증가
					student[i]--; // 내 체육복 수 감소
					answer++; // 카운팅
				} else if ((i + 1) <= n && student[i + 1] == -1) {// 다음 학생이 존재하고 + 그 학생이 도난당한 학생이라면
					student[i + 1]++;
					student[i]--;
					answer++;
				}
			}
		}

		return answer; // 카운팅 return
	}

	public static void main(String[] args) {
		PM_그리디_체육복 pm = new PM_그리디_체육복();
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		System.out.println(pm.solution(n, lost, reserve));
	}

}
