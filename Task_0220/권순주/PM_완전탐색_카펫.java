import java.util.Arrays;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 크기로 만들 수 있는 카펫의 가로와 세로 길이 
 * 문제 유형 : 구현
 * 요구 개념 : 완전탐색
 * <풀이법 요약> 
 * 끄적이다가 발견함 - 갈색에서 4개의 모서리를 제거한 뒤 반으로 쪼개서 넓이가 노랑이 되는 값들을 찾으면 된다.
 * 1. brown에 해당하는 4개의 모서리 사각형을 빼준 뒤 2를 나눠 size에 저장 (결국 가로 세로의 길이가 겹치기 때문에)
 * 2. size를 또 반으로 나눠서 각각 가로와 세로의 길이로 사용
 * 3. 1부터 size의 반까지 반복문을 돌리면서 가로와 세로 길이 지정
 * 4. 가로X세로의 값이 yellow와 같을 때를 찾아줌 (w,h는 yellow의 가로 세로 길이이기 때문에 brown의 가로 세로를 구하기 위해 양쪽 모서리 사각형2개를 추가)
 * 5. 4의 조건에 해당하는 가로와 세로의 값 중은 무조건 w<=h을 성립하기 때문에 조건에 맞게 answer에 반대로 넣어준다.
 */

public class PM_완전탐색_카펫 {

	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int size = (brown - 4) / 2; // brown에 해당하는 4개의 모서리 사각형을 빼준 뒤 2를 나눠준다
		for (int i = 1; i <= size / 2; i++) { // 1~size/2를 반복하면서 가로 세로 찾아주기
			int w = i;
			int h = size - i;
			if (w * h == yellow) { // 만약 가로 세로의 곱이 yellow라면 멈춤
				answer[0] = h + 2; // w>=h를 만족하기 때문에 반대로 넣어줌
				answer[1] = w + 2; // w,h는 yellow의 가로 세로 길이이기 때문에 brown의 가로 세로를 구하기 위해 양쪽 모서리 사각형2개를 추가함
				break;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		PM_완전탐색_카펫 pm = new PM_완전탐색_카펫();
		int brown = 10;
		int yellow = 2;
		System.out.println(Arrays.toString(pm.solution(brown, yellow)));
	}

}
