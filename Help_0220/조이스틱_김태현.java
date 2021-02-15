package Greedy;

/*** 
 * <문제 요약>
 * 구해야 하는 것 : 이름을 완성시킬 조이스틱 최소 조작 횟수.
 * 문제 유형 : 구현, 그리디.
 * 요구 개념 : 문자열 변환.
 * <풀이법 요약>
 * 목표 문자열과 같은지, 다른지를 나타내는 boolean 배열을 만든다.
 * 1. 이름 설정 기준(구현)
 * -> 이동 방법은 총 두가지 아래 경우 중 최소를 선택한다.
 * -> (1) 위로 이동하여 도달
 * -> (2) 아래로 이동하여 도달
 * 2. 이동 관련 기준(그리디)
 * -> 각 지점에서 최소 지점으로 이동
 * -> 단, 오른쪽/왼쪽으로 가는 경우가 같은 경우 오른쪽을 우선하여 움직인다.
 * -> (왜냐하면, 왼쪽으로 이동하는 경우 이미 1번의 이동을 추가로 하게 되어있음).
 * 3. 1과 2의 값을 합친다.
 * <이해가 안되는 부분>
 * 문제가 풀리긴 풀리는데
 * 그리디 정당성을 증명하지 못하겠음(왜 이게 되는건지 모르겠음).
 */

public class 조이스틱 {
	public static void main(String[] args) {
		조이스틱 doit = new 조이스틱();
		String name = "JEROEN";
		doit.solution(name);
	}

	public int solution(String name) {
		int answer = 0;
		// 목표 문자열과 같은지, 다른지를 나타내는 boolean 배열을 만든다.
		boolean[] correct = new boolean[name.length()];
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == 'A') {
				correct[i] = true;
			}
		}
		// 바꿔야 하는 이름을 하나씩 탐색하며, 최솟값을 더해 나간다.
		int change = 0;
		for (int i = 0; i < correct.length; i++) {
			if (!correct[i]) {
				change = change + Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
			}
		}

		int l_idx = 0, r_idx = 0;
		int s_idx = 0;
		int move = 0;
		while (true) {
			// 전체가 true가 되면 최종 계산후 종료.
			boolean flag = true;
			for (int i = 0; i < correct.length; i++) {
				if (!correct[i]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				answer = change + move;
				return answer;
			}
			// 각 출발 지점에서 오른쪽으로 가는 것이 짧은지, 왼쪽으로 가는 것이 짧은지 판단하여 최종값 계산.
			int r_cnt = 0;
			r_idx = s_idx;
			while (true) {
				if (!correct[r_idx]) {
					break;
				}
				r_idx++;
				r_cnt++;
				if (r_idx >= name.length()) {
					r_idx = 0;
				}
			}
			int l_cnt = 0;
			l_idx = s_idx;
			while (true) {
				if (!correct[l_idx]) {
					break;
				}
				l_idx--;
				l_cnt++;
				if (l_idx <= -1) {
					l_idx = name.length() - 1;
				}
			}
			// 왼쪽으로 가는 것이 짧으면, 왼쪽으로 이동.
			if (r_cnt > l_cnt) {
				s_idx = l_idx;
				move += l_cnt;
			}
			// 오른쪽으로 가는 것이 짧거나 같다면, 오른쪽으로 이동.
			// 같은 경우 오른쪽으로 우선해서 가는 경우는, 오른쪽으로 배열을 한바퀴 도는 것이 왼쪽으로 배열을 도는 것 보다 -1만큼 작기 때문이다.
			else {
				s_idx = r_idx;
				move += r_cnt;
			}
			correct[s_idx] = true;
		}
	}
}
