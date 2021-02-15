package BruteForce;

/***
 * <문제 요약>
 * 구해야 하는 것 : 조건에 맞는 카펫의 가로, 세로 길이.
 * 문제 유형 : 완전 탐색
 * 요구 개념 : 완전 탐색 규칙성 찾기
 * <풀이법 요약>
 * 1. 가로 길이 >= 세로 길이 임을 이용하여 정답을 찾을때까지 아래 과정을 반복한다.
 * -> 세로 길이가 가장 짧은 시점(3)부터 ~ (가로 길이 >= 세로 길이)인 시점까지
 * -> 갈색 길이를 먼저 계산
 * -> 노란색 개수를 계산하고 검증
 */

public class 카펫 {
	public static void main(String[] args) {
		카펫 doit = new 카펫();
		int brown = 10;
		int yellow = 2;
		doit.solution(brown, yellow);
	}

	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		// 세로 길이는 최소 3부터 시작한다.
		int col = 3;
		// 세로 길이가 정해지면 가로 길이는 자연스레 정해진다.
		int row = (brown - col * 2) / 2 + 2;
		// 가로 길이 >= 세로 길이 일때 까지만 반복.
		while (row >= col) {
			// 가로, 세로 길이가 정해지면 노란색 개수를 쉽게 구할 수 있다.
			// 일치하면 반복문 탈출.
			if(yellow == (row - 2) * (col - 2)) {
				answer[0] = row;
				answer[1] = col;
				break;
			}
			// 일치하지 않으면 계속 검증 진행.
			col++;
			row = (brown - col * 2) / 2 + 2;
		}
		return answer;
	}
}
