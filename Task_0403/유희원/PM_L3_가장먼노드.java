package Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 1번 노드에서 가장 멀리 떨어진 노드의 갯수 return
 */
public class PM_L3_가장먼노드 {

	static int n = 6;
	static int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
	public static void main(String[] args) {
		System.out.println(solution(n,edge));

	}
	 static int[] dist;
	 static boolean[][] visit;
	 public static int solution(int n, int[][] edge) {
	        int answer = 0;
	        
	        dist = new int[n+1];
	        visit = new boolean[n+1][n+1];
	        //무향. 양쪽다 체크
	        for (int i = 0; i < edge.length; i++) {
				visit[edge[i][0]][edge[i][1]] = true;
				visit[edge[i][1]][edge[i][0]] = true;
			}
	        Queue<Integer> queue = new LinkedList<>();
	        //1에서 가장 먼 노드니까 1부터 넣고
	        queue.add(1);
	        int max = 0;
	        
	        //BFS
	        while(!queue.isEmpty()) {
	        	//빼서
	        	int idx = queue.poll();
	        	for (int i = 2; i <=n; i++) {
	        		//아직 dist 결정 안했고 연결되어있는 노드면
					if(dist[i]==0&&visit[idx][i]) {
						//뺀 노드 dist 값에 1 더해줌
						dist[i] = dist[idx]+1;
						//다음 탐색을 위해 i 다시 넣어줌
						queue.add(i);
					}
				}
	        }
	        for (int i = 0; i <=n; i++) max = Math.max(max, dist[i]);
	        for (int i = 0; i <=n; i++) {
	        	//최댓값 갯수
				if(max == dist[i]) answer++;
			}
	        
	        return answer;
	    }

}
