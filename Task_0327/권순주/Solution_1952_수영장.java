import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 수영장을 이용할 수 있는 가장 적은 비용
 * 문제 핵심 요약 : dp
 * 
 * <풀이법 요약>
 * [점화식]
 * f(n) - min(f(n-1)+cost[0]*month[n], f(n-1)+cost[1]) (i<=3)
 * 		- mint(min(f(n-1)+cost[0]*month[n], f(n-1)+cost[1]), f(n-3)+cost[2]) (i>3)
 * => min(f(n), cost[3])
 * 
 * 1. 1일 이용권 = 저번달 누적 비용 + 이용 횟수 * 1일권 비용
 * 2. 1달 이용권 = 저번달 누적 비용 + 1달권 비용
 * 3. 3달 이용권 = 3달 전 누적 비용 + 3달권 비용
 * 4. 1년 이용권 = 1년권 비용
 * 
 */

public class Solution_1952_수영장 {

	static int[] cost;
	static int[] month;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			cost = new int[4]; // 이용권 비용 저장
			month = new int[13]; // 각 달의 이용 계획 저장
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			System.out.printf("#%d %d\n", t, dp());
		}
	}

	private static int dp() {
		int[] d = new int[13]; // 메모이제이션
		int answer = 0; // 이용 계획이 없는 달의 값을 저장하기 위한 변수
		for (int i = 1; i < 13; i++) {
			if (month[i] == 0) { // 만약 이용 계획이 없는 달일 때 
				d[i] = answer; // 이전 달의 값 저장
				continue;
			}
			answer = Math.min(d[i - 1] + cost[0] * month[i], d[i - 1] + cost[1]); // 1일 이용권 vs 1달 이용권
			if (3 < i) { // 월이 3보다 클 때 
				answer = Math.min(answer, d[i - 3] + cost[2]); // 1일 이용권 vs 1달 이용권 vs 3달 이용권
			}
			d[i] = answer; // 가장 최소값을 저장
		}
		return Math.min(d[12], cost[3]); // 1일 이용권 vs 1달 이용권 vs 3달 이용권 vs 1년 이용권
	}

}
