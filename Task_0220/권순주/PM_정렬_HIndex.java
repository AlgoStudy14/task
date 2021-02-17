import java.util.Arrays;

/***
 * <문제 요약> 
 * 구해야 하는 것 : H-index 
 * 문제 유형 : 구현, 문제가 왜 정렬에 있는지 이해하고 풀이를 시작하는 것이 중요한듯..?
 * 요구 개념 : 정렬
 * <풀이법 요약> 
 * 1. 배열 정렬
 * 2. 배열을 오른쪽 index부터 판단(내림차순 정렬시 스킵 가능)
 * 3. 결국 현재 index에 해당하는 value값이 count를 넘는 것이 이 문제의 핵심!이기 때문에 (구현 코드에서 count=answer)
 * 	  for문을 돌려서 계속 value값이 count를 넘기는지 확인해준다 (같거나 같으면 빠져나오도록)
 * 4. value값이 count를 넘기지 않는다면 count를 증가
 */

public class PM_정렬_HIndex {

	public static void main(String[] args) {
		PM_정렬_HIndex pm = new PM_정렬_HIndex();
		int[] citations = new int[] { 4, 0, 6, 1, 5 };
		int answer = pm.solution(citations);
		System.out.println(answer);
	}

	public int solution(int[] citations) {
		int answer = 0;
		int len = citations.length;
		Arrays.sort(citations); // citations을 내림차순 정렬

		for (int i = len - 1; i >= 0; i--) { // 큰 값부터 판단
			if (citations[i] <= answer) { // 만약 count한 값이 배열의 value보다 크거나 같다면 break;
				break;
			}
			answer++; // 논문의 개수 증가
		}

		return answer;
	}
}
