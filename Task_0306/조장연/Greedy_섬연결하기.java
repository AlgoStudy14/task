
/*
 * <문제 요약>
 * 섬과 섬끼리 연결하는 다리의 cost가 주어졌을 때 모든 섬을 연결하는 최소 cost구하기
 * (연결할 수 없는 섬은 주어지지 않았음)
 * 
 * <풀이법 요약>
 * 다익스트라 알고리즘 사용
 * 
 * 다익스트라*
 * 1. 해당 섬에서 연결된 다리의 cost를 나타내는 map 생성
 * ---------
 * 2. 0번 섬을 기준으로 가장 거리가 짧은 섬 선택
 * 3. 선택된 섬에서 갈수 있는 다른 섬까지의 cost와 0번째 섬에서 갈수 있는 cost 비교
 * (만약에 선택된 섬에서 갈 수 있는 cost가 더 작은 경우 0번을 기준으로 작은 cost를 덮어 씌움)
 * (0 -> 선택된 노드 -> 해당 섬의 경로가 된다.)
 * 4. 0번을 기준으로 연결되지 않고 가장 최소 cost인 곳 선택
 * ---------
 * 5. 위의 과정(-----안에 부분)을 모든 섬을 연결할 때 까지 반복 
 */

public class Greedy_섬연결하기 {

	public static void main(String[] args) {
		Greedy_섬연결하기 pm = new Greedy_섬연결하기();
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		System.out.println(pm.solution(n, costs));
	}

	public int solution(int n, int[][] costs) {
		int answer = 0;
		int checkNum = 1;
		boolean[] check = new boolean[n];
		check[0] = true;
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < costs.length; i++) {
			map[costs[i][0]][costs[i][1]] = costs[i][2];
			map[costs[i][1]][costs[i][0]] = costs[i][2];
		}

		while (checkNum < n) {
			int min = Integer.MAX_VALUE;
			int minNum = 0;
			for (int i = 0; i < n; i++) {
				if (min > map[0][i] && check[i] == false) {
					min = map[0][i];
					minNum = i;
				}
			}

			for (int i = 0; i < n; i++) {
				if (map[minNum][i] < map[0][i])
					map[0][i] = map[minNum][i];
			}

			check[minNum] = true;
			checkNum++;
			answer += min;
		}

		return answer;
	}

}
