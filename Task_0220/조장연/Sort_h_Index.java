import java.util.Arrays;

/* <문제 요약>
 * 논문 n편중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용된 h의 최댓값 구하기
 * 
 * < 풀이법 요약>
 * 배열을 sorting 해서 검색이 용이하게 바꿔줌
 * count를 +1 해가면서 뒤에서부터 비교하면 
 * 1. h번 이상 인용된 논문이 h편 이상인가(h = count)
 * 2. 나머지 논문이 h번 이하 인용되었는가(sorting을 이미 해줬기 때문에 생각할 필요없음)
 * 문제의 조건을 만족할 수 있게 됨!
 * */

public class Sort_h_Index {

	public static void main(String[] args) {
		Sort_h_Index pm = new Sort_h_Index();
		int[] citations = new int[] { 4, 0, 6, 1, 5 };
		int answer = pm.solution(citations);
		System.out.println(answer);
	}

	public int solution(int[] citations) {
		int answer = 0;
		Arrays.sort(citations); // h번 이상 인용된 논문 찾기 편하게
		int count = 1;

		for (int i = citations.length - 1; i > -1; i--) {
			if (count <= citations[i]) { // h편 이상 논문이 인용되었는지 확인
				count++;
				answer++;
			} else {
				break;
			}
		}

		return answer;
	}
}
