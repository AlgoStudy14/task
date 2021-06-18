import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* (완)
 * <문제 유형>
 * 문제 정의 : 주어진 내구도와 무게를 가지고 조건에 맞게 깰 수 있는 최대 계란 개수
 * 문제 유형 : 백트래킹
 * 주의 사항 : 자기 자신을 꺨 수 없다
 * 
 * <풀이 요약>
 * 깰 수 있는 계란이 있다면 dfs를 진행한다.
 * 가지치기
 * 1. 자기 자신일 때는 제외한다.
 * 2. 현재 계란이 깨져있다면 다음으로 넘어간다.
 * 3. 깰 수 있는 계란이 존재하지 않을 때 종료
 * 4. 가장 오른쪽 계란일 때 종료
 * 
 * <피드백>
 * 작은 반례를 만들어보면서 디버깅하자
 * 
 * <반례>
 * 3
 * 3 100
 * 100 1
 * 100 1
 * 2
 */

public class BOJ_S1_16987_계란으로_계란치기 {

	static int N;
	static int[] weight, hp;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		hp = new int[N];
		weight = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			hp[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}

		func(0);
		System.out.println(answer);
	}

	private static void func(int index) {
		// 가장 오른쪽 계란일 때
		if (index == N) {
			answer = Math.max(answer, countEgg());
			return;
		}

		// 현재 계란이 깨져있을 때
		if (hp[index] <= 0) {
			func(index + 1);
		}

		// 깰 수 있는 계란이 있는지 판별
		boolean checkAble = false;
		// 범위 비교
		for (int i = 0; i < N; i++) {
			// 자기 자신은 제외
			if (index == i) {
				continue;
			}
			// 깰 수 있는 계란일 떄
			if (hp[i] > 0 && hp[index] > 0) {
				checkAble = true;
				hp[i] -= weight[index];
				hp[index] -= weight[i];
				func(index + 1);
				hp[i] += weight[index];
				hp[index] += weight[i];
			}
		}

		// 꺨 수 있는 계란이 없을 때
		if (!checkAble) {
			answer = Math.max(answer, countEgg());
			return;
		}
	}

	// 깨진 계란 세기
	private static int countEgg() {
		int eggCnt = 0;
		for (int i = 0; i < N; i++) {
			if (hp[i] <= 0) {
				eggCnt++;
			}
		}
		return eggCnt;
	}

}
