package algo0502;

/***
 * <문제 요약>
 * 구해야 하는 것 : 주어진 문자열을 암호화한 암호문을 출력
 * 제약 사항 : 문자열과 삽입할 키워드와 같을 경우 건너뛰기 중단하고 삽입
 * <풀이법 요약>
 * 1. substr(시작~건너뛸 위치)한 문자열 중 keyword와 일치하는 문자가 있는지 체크
 *  1-1. 있다면 그 뒤(startIdx + 1)에 삽입하고 시작위치 재설정
 *  1-2. 없다면 건너뛸 위치(startIdx + skip)에 삽입하고 시작위치 재설정
 * 2.(건너뛴 글자 수 < sentence의 길이)라면 stop
 */

public class Solution2 {

	public static void main(String[] args) {
//		String sentence = "i love coding";
//		String keyword = "mask";
//		solution(sentence, keyword, new int[] {0, 0, 3, 2, 3, 4});
//		String sentence = "i love coding";
//		String keyword = "mode";
//		solution(sentence, keyword, new int[] {0, 10});
//		String sentence = "abcde fghi";
//		String keyword = "xyz";
//		solution(sentence, keyword, new int[] {10, 0, 1});
//		String sentence = "encrypt this sentence";
//		String keyword = "something";
//		solution(sentence, keyword, new int[] {0, 1, 3, 2, 1, 2, 0, 3, 0, 2, 4, 1, 3});
		String sentence = "abc";
		String keyword = "abc";
		solution(sentence, keyword, new int[] {0, 1, 0, 2, 3, 4});
	}
	
	public static void solution(String sentence, String keyword, int[] skips) {
		StringBuilder sb = new StringBuilder(sentence);
		
		int length = sentence.length();		// 14
		int kl = keyword.length();			// 4
		int startIdx = 0;
		int keyIdx = 0;
		int skipCnt = 0;

		for (int skip : skips) {
			// 현재 문자열의 길이
			int nl = sb.length();
			
			// 추가할 키워드와 문자열의 문자가 같은지 비교
			int toIdx = startIdx + skip > nl ? nl : startIdx + skip;
			int same = sb.substring(startIdx, toIdx).indexOf((keyword.charAt(keyIdx)));
			if (same != -1) {
				// 겹치는 위치 뒤에 추가하므로 1을 더해줌
				startIdx += same + 1;
				skipCnt += same + 1;
			} else {
				startIdx += skip;
				skipCnt += skip;
			}
			
			// 건너뛴 글자수가 원래 sentence의 길이를 초과할 경우 stop
			if (skipCnt > length) break;
			sb.insert(startIdx++, keyword.charAt(keyIdx));
			
			// 다음에 넣을 키워드의 위치
			keyIdx = (keyIdx + 1) % kl;			
		}
		
		System.out.println(sb.toString());
	}
}
