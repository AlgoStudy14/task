import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 총 공을 던지는 횟수
 * 문제 핵심 요약 : 시뮬레이션으로 풀면 된다
 * <풀이법 요약> 
 * 1. 한 사람이 공을 M번 받을 때 까지 무한 loop
 * 2. 공을 던질 사람이 받은 횟수가 짝수라면 index L만큼 증가
 * 3. 공을 던질 사람이 받은 횟수가 홀수라면 index L만큼 감소(0보다 작을 때 판별)
 * 4. 카운팅
 */

public class Main_Bronze2_1592 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] people = new int[N];

		int cnt = 0; // 카운팅 저장 변수
		int index = 0; // 공 던질사람 index 저장 변수
		while (true) {
			people[index]++; // index번째 사람 공 받는 횟수 증가
			if (people[index] == M) // 한 사람이 공을 M번 받았을 때
				break;
			if (people[index] % 2 != 0) { // 홀수일 때
				index = (index + L) % N; // 시계 방향으로 L번째 있는 사람
			} else { // 짝수일 때
				index = (index - L) % N; // 반시계 방향으로 L번째 있는 사람
				if (index < 0) { // 0보다 작아진다면
					index += N; // N더해주기
				}
			}
			cnt++; // 카운팅
		}

		System.out.println(cnt);
	}

}
