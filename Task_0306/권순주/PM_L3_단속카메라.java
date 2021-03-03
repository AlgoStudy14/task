import java.util.Arrays;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 단속 카메라의 개수
 * 문제 핵심 요약 : 범위를 줄여가면서, 범위가 존재하지 않게 될 때 단속카메라 수를 증가
 * <풀이법 요약> 
 * 결국 범위가 겹치는 차량은 단속카메라가 하나만 필요하기 때문에 범위를 줄여주는 방법으로 접근했다!
 * 1. 진입 지점순으로 정렬
 * 2. 진입 지점이 가장 작은 차량의 진입,진출 지점을 저장
 * 3. 반복
 * 3-1. 다음 차량의 진입 지점과 2.를 비교하여 더 큰 진입 지점을 tmp에 저장
 * 3-2. 다음 차량의 진출 지점과 2.를 비교하여 더 작은 진출 지점을 tmp에 저장
 * 위 두 과정은 범위를 줄여주는 과정
 * 3-3. 만약 범위가 존재한다면 두 지점을 swap!
 * 3-4. 만약 범위가 존재하지 않는다면 다음 진입,진출 지점을 가지고 다시 시작 reset!
 * 	그렇다는건 단속카메라가 하나 더 필요하다는 것이기 때문에 answer 증가
 */

public class PM_L3_단속카메라 {

	public int solution(int[][] routes) {
		int answer = 1;
		Arrays.sort(routes, (a, b) -> Integer.compare(a[0], b[0])); // 진입 지점순으로 정렬
		int input = routes[0][0]; // 진입 지점 저장
		int output = routes[0][1]; // 진출 지점 저장
		for (int i = 1; i < routes.length; i++) { // 범위를 줄여가면서 체킹
			int tmpInput = Math.max(input, routes[i][0]); // 진입 자점은 둘 중에 큰 값을
			int tmpOutput = Math.min(output, routes[i][1]); // 진출 지점은 둘 중에 작은 값을 저장
			if (tmpOutput - tmpInput >= 0) { // 범위가 존재하면
				input = tmpInput; // tmp를 진입 지점으로 바꿔주기 swap!
				output = tmpOutput; // tmp를 진출 지점으로 바꿔주기 swap!
			} else { // 범위가 엇갈리면 즉 존재하지 않는다면
				input = routes[i][0]; // 진입 지점  reset!
				output = routes[i][1]; // 진출 지점 reset!
				answer++; // reset하면 단속카메라 수 증가!
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		PM_L3_단속카메라 pm = new PM_L3_단속카메라();
		int[][] routes = { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };
		System.out.println(pm.solution(routes));
	}

}
