import java.io.BufferedReader;
import java.io.InputStreamReader;

/* (미완)
 * <문제 요약>
 * 문제 정의 : N번째 감소하는 수 구하기
 * 문제 유형 : 백트래킹, dfs
 * 주의 사항 : 감소하는 수의 최대값 : 9876543210
 * 
 * <풀이 요약>
 * 자리수를 바꿔가면서 감소하는 수를 구한다
 * -> Array?Queue?StringBuilder?
 * 어떤것을 써야할지? 어떻게 접근해야할지? 아이디어가 떠오르지 않는다ㅜㅜ
 * 
 * <피드백>
 * 길이를 바꿔가는 부분을 어떻게 처리해야 할지 아직 모르겠다
 * 다른 사람들의 풀이를 보고 다시 풀거나 능숙함을 길러서 다시 도전하자!
 */

public class BOJ_G5_1038_감소하는_수 {
	
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
	}

}
