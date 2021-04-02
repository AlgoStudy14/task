import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_L3_가장_먼_노드 {

	static class Node { // Node의 정보를 담고 있는 객체
		int v;
		int dis;

		public Node(int v, int dis) {
			this.v = v;
			this.dis = dis;
		}
	}

	static ArrayList<Integer> list[]; // 그래프 list

	public int solution(int n, int[][] edge) {
		list = new ArrayList[n];
		for (int i = 0; i < n; i++) { // 리스트 초기화
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < edge.length; i++) { // 리스트 정보 담아주기
			list[edge[i][0] - 1].add(edge[i][1] - 1);
			list[edge[i][1] - 1].add(edge[i][0] - 1);
		}
		return bfs(n);
	}

	private static int bfs(int n) {
		int[] count = new int[n]; // 각 거리별 노드의 개수 저장할 배열
		boolean[] visited = new boolean[n]; // 노드 방문 체크
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 1)); // node : 0 , 거리 : 1로 시작
		visited[0] = true;

		int max = -1; // 최대 거리를 측정할 변수
		while (!q.isEmpty()) {
			Node current = q.poll(); // 노드 뽑기
			max = Math.max(max, current.dis); // 거리 최댓값 저장
			for (Integer next : list[current.v]) { // 연결된 노드 불러옴
				if (!visited[next]) { // 방문한 노드가 아니라면
					count[current.dis]++; // 해당 거리의 노드 count++
					visited[next] = true; // 노드 방문 체크
					q.offer(new Node(next, current.dis + 1)); // 거리 + 1해서 큐에 넣어주기
				}
			}
		}

		return count[max - 1]; // 최대 길이 index에 해당하는 count의 수를 return
	}

	public static void main(String[] args) {
		Solution_L3_가장_먼_노드 pm = new Solution_L3_가장_먼_노드();
		int n = 6;
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		System.out.println(pm.solution(n, edge));
	}

}
