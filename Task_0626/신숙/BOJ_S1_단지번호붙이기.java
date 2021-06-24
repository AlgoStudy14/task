import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S1_단지번호붙이기 {
	/*
	 * <문제>
	 * 지도가 주어질 때, 1은 집이 있다는 것을 뜻한다. 서로 연결되어있는 집들 모임을 단지라고 표현하며 이 단지들을 번호를 붙일때,
	 * 몇 개의 단지가 있는지와 단지에 집에 몇 채 씩 있는지 `오름차순`으로 출력하라.
	 * 
	 * <문제풀이>
	 * BFS. 
	 * 맵을 탐색하며 1을 만난다면 Queue에 넣어서 사방탐색으로 단지번호를 붙임.
	 * 마지막 단지 번호 출력 
	 * 	+ 각 단지마다(하나의 함수 안에서) 조건에 맞아서 bfs q에 넣을때마다 count 늘려서 우선순위 q로 출력.
	 */
	static int N, D_num;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			for(int j = 0; j < N; j++)
				map[i][j] = s.charAt(j) - '0';
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//map에서 집이여야하고 방문하지 않았을 때
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					D_num++;
				}
			}
		}
		System.out.println(D_num);
		while(!pq.isEmpty())
			System.out.println(pq.poll());
	}
	
	static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		//단지내에 집이 몇채인지 알아내기위해.
		int count = 1;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				
				if(nr >= 0 && nc >= 0 && nc < N && nr < N && map[nr][nc] == 1 && !visited[nr][nc]) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					count++;
				}
			}
		}
		pq.add(count);
	}
}
