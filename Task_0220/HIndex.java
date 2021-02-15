package Sort;

import java.util.Arrays;
import java.util.Collections;

/***
 * <문제 요약> 
 * 구해야 하는 것 : H-index. 
 * 문제 유형 : 구현(문제 이해). 
 * 요구 개념 : 정렬. 
 * <풀이법 요약> 
 * 1. citation을 내림 차순 정렬한다. 
 * 2. 정렬된 배열에서 (인덱스 + 1 = 논문의 편수)임을 이용하여 (논문의 편수)를 기준으로 H-Index를 탐색한다.
 */

public class HIndex {
	public static void main(String[] args) {
		HIndex doit = new HIndex();
		int[] citations = new int[] { 3, 0, 6, 1, 5 };
		doit.solution(citations);
	}

	public int solution(int[] citations) {
		int answer = 0;
		// 1. citation 내림차순 정렬. 굳이 reverseOrder()을 사용하지 않고
		// 일반 정렬후 2 단계에서 인덱스를 이용해도 된다.
		Integer[] temp = new Integer[citations.length];
		for (int i = 0; i < citations.length; i++) {
			temp[i] = citations[i];
		}
		Arrays.sort(temp, Collections.reverseOrder());

		// 2. 인덱스 + 1은 논문의 편수. 인용횟수가 h편 이상인 지점이라면, 인덱스를 갱신한다.
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] >= i + 1) {
				answer = i + 1;
			}
		}

		return answer;
	}
}
