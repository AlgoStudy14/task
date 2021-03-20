import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 홈방범 서비스가 가능한 최대 집 수
 * 문제 핵심 요약 : 모든 집의 위치 list 저장 + 현재 위치와 모든 집 위치 사이의 거리를 이용해 집의 수를 구함
 * 
 * <풀이법 요약>
 * 핵심은 해당 위치와, 저장된 모든 집의 위치에 대해서 거리를 증가시키며 조사하는 방법이다
 * 
 * 1. list에 해당 집의 위치를 저장한다.
 * 2. 모든 거리에 따른 값을 조사한다. 
 * 2-1. 현재 위치와 집 사이의 거리이기 때문에 거리는 0~N 사이에 존재한다.
 * 3. 거리에 따른 해당 가격을 저장한다.
 * 3-1. 만약 0이라 하면 실제로는 거리가 1을 나타내기 때문에 cost 비용 변경이 필요하다.
 * 3-2. k*k+(k-1)*(k-1) => (k+1)*(k+1)+k*k 이거 생각 못해서 오래걸림 ㅜㅜ
 * 4. list에 있는 모든 집에 대해서 조사한다.
 * 4-1. 만약 해당 위치와 집의 위치의 거리가 k라면 집의 개수를 증가시킨다.
 * 5. 조건에 맞는 집이 존재한다면 answer를 계산한다.
 * 6. 2중 for문을 돌며 모든 위치에 대해 2~5를 반복한다. 
 */

public class Solution_2117_홈_방범_서비스 {

	static class Home {
		int x;
		int y;

		public Home(int x, int y) {
			this.x = x;
			this.y = y;
		}		
		
	}

	static int N, M;
	static int answer;
	static ArrayList<Home> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						list.add(new Home(i, j)); // 해당 집의 위치를 list에 담아주기
					}
				}
			}
			answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					check(i, j); // 전체 위치를 돌면서 체크
				}
			}

			System.out.printf("#%d %d\n", t, answer);
		}
	}

	private static void check(int x, int y) {
		int cnt = 0; // 포함되는 집의 개수
		int cost = 0; // 총비용
		for (int k = 0; k <= N; k++) { // 거리 현재 위치와의 거리 범위 0<= <=N
			cost = k * k + (k + 1) * (k + 1); 

			for (Home home : list) {
				if (Math.abs(home.x - x) + Math.abs(home.y - y) == k) {
					cnt++; // 해당 위치와의 거리차이가 k개인 house를 count -> 누적
				}
			}

			if (cnt > 0) {
				if (cost <= cnt * M) { // 손해를 보지 않는다면
					answer = Math.max(answer, cnt);
				}
			}
		}
	}

}
