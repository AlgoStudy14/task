import java.util.LinkedList;
import java.util.Queue;

public class PM_L3_가장먼노드 {
	/*
	 * 그래프 = BFS.
	 * 1번노드에서 가장 먼노드가 몇개인지.		=> 특정 노드부터 최단거리연결 => 다익스트라?		=> 근데 간선에 가중치가 없음. 가중치를 1로두던가.
	 * 
	 * bfs로
	 * 
	 * 테스트 7 〉	통과 (3153.37ms, 250MB)
	      테스트 8 〉	통과 (8650.70ms, 552MB)
              테스트 9 〉	통과 (7683.07ms, 556MB)
	 * 얼마나 깊길래...?			=> 다익스트라로 풀어야하는 문제엿나?
	 */
	static int ans;
	public static void main(String[] args) {
		ans = Integer.MAX_VALUE;
		int[][] edge = { {3,6}, {4,3}, {3,2}, {1,3}, {1,2}, {2,4}, {5,2} };
		
		System.out.println(solution(7, edge));
	}
	
	static int solution(int n, int[][] edge) {
		Queue<Integer> q = new LinkedList<Integer>();
		int size = 0, next = 0;
		boolean[] visit = new boolean[n];
		boolean[][] map = new boolean[n][n];
		for(int i = 0; i < edge.length; i++) {
			map[edge[i][0] - 1][edge[i][1] - 1] = true;
			map[edge[i][1] - 1][edge[i][0] - 1] = true;
		}
		//위 edge 때문에 0부터 시작했으므로 문제에선 1번부터 시작이니 0부터 시작으로
		visit[0] = true;
		q.offer(0);	
		while(!q.isEmpty()) {
			size = q.size();
			for(int i = 0; i < size; i++) {
				next = q.poll();
				for(int j = 0; j < n; j++) {
					if(map[j][next] && !visit[j]) {
						visit[j] = true;
						q.add(j);
					}
				}
			}
			ans = size;
		}
		return ans;
	}
}