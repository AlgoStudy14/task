package Greedy;

import java.util.Arrays;

/***
 * <문제 요약>
 * 구해야 하는 것 : 필요한 카메라의 개수.
 * 문제 유형 : 그리디
 * 요구 개념 : 정렬.
 * <풀이법 요약>
 * 1. 시작지점을 기준으로 자동차의 경로를 오름차순 정렬한다. 
 * 2. 초기 카메라의 위치는 첫 진입 차량의 진출 지점으로 설정하고, 각 진입 차량의 진출 지점과 카메라의 위치를 비교하며 다음과 같은 과정을 수행한다. 
 * -> 진출 지점이 카메라의 위치보다 앞에 있다면, 카메라의 위치를 해당 진출 지점으로 변경. 
 * -> 진입 지점이 카메라의 위치보다 뒤에 있다면, 카메라의 위치를 해당 진출 지점으로 변경 후 카메라의 대수 + 1.
 */

public class PM_L3_단속카메라 {
	public static void main(String[] args) {
		PM_L3_단속카메라 doit = new PM_L3_단속카메라();
		int[][] routes = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };
		doit.solution(routes);
	}

	public int solution(int[][] routes) {
		int answer = 0;
		int cam_pos = Integer.MIN_VALUE;

		// 시작 지점을 기준으로 오름차순 정렬.
		Arrays.sort(routes, (x, y) -> Integer.compare(x[0], y[0]));

		for (int i = 0; i < routes.length; i++) {
			if (routes[i][1] < cam_pos) {
				cam_pos = routes[i][1];
				continue;
			}
			if (routes[i][0] > cam_pos) {
				cam_pos = routes[i][1];
				answer++;
			}
		}

		return answer;
	}
}
