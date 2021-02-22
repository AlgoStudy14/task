import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 문자열에서 같은 번호로 붙어있는 쌍들을 소거하고 남은 번호로 비밀번호 만들기
 * 문제 핵심 요약 : 마지막 문자열을 비교해서 같으면 제거 다르면 추가
 * <풀이법 요약> 
 * 1. StringBuilder를 사용
 * 2. 패스워드 길이만큼 반복하면서 조건 체크
 * <조건>
 * (1). 길이가 0이 아니고 (0이면 index에 접근할 수 없으니까)
 * (2). 저장하는 문자열의 마지막 문자가 패으워드의 index에 해당하는 문자와 같다면
 * => 저장 문자열의 마지막 문자 제거
 * 3. 조건에 맞지 않는다면 저장 문자열에 문자 추가
 */

public class Solution_D3_1234 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String pass = st.nextToken();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < N; i++) { // 패스워드의 크기만큼 반복
				if (sb.length() != 0 && sb.charAt(sb.length() - 1) == pass.charAt(i)) {
					// 만약 길이가 0이 아니고 + 저장하는 문자열의 마지막 문자가 패스워드의 index에 해당하는 문자와 같다면
					sb.deleteCharAt(sb.length() - 1); // 저장 문자열의 마지막 문자 제거
				} else { // 조건에 맞지 않으면
					sb.append(pass.charAt(i)); // 저장 문자열에 추가 
				}
			}

			System.out.printf("#%d %s\n", t, sb);
		}
	}

}
