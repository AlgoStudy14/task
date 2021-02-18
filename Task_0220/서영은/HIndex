package algoStudy;

import java.util.Arrays;

/**
 * <문제 요약>
 * 구해야 하는 것: 입력 받은 int 배열을 통해 H-index 구하가 
 * 제약 사항: n편 중, h번 이상 인용된 논문이 h편 "이상", 나머지는 h번 이하 인
 * 문제 유형: 정렬 
 * 요구 개념: 정렬 
 * 
 * <풀이법 요약>
 * 1. citations 배열을 오름차 정렬 
 * 2. 앞에서부터 차례로 체크하여 h-index 체크하고, 조건에 부합하는 값이 최댓값이므로 반복문 탈출 
 * 3. 답 반환 
 *
 */
public class programmers_HIndex {

	public static void main(String[] args) {
		int answer = solution(new int[]{3,0,6,1,5});
		
		System.out.println(answer);

	}
	
	public static int solution(int[] citations) {
		int answer = 0;
		int h=0;
		int k=0;
		Arrays.sort(citations);
		for (int i = 0; i < citations.length; i++) {
			h=citations[i];
			k=citations.length-i;
			
			if(k<=h) {
				answer=k;
				break;
			}
			
		}
		return answer;
    }

}
