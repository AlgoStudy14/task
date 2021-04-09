package algo0410;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/* S1
 * 비의 양 h에 따라 높이가 h이하인 지점이 물에 잠긴다고 가정했을때
 * 잠기지 않고 붙어있는 영역은 한 영역으로 카운팅 = 안전지역 (꼭짓점으로 붙어있는건 x)
 * 안전영역 최대갯수 출력
 */
public class BOJ_2468_안전영역 {

	static int N,max,cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static class Node{
		int x,y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static Queue<Node> que = new LinkedList<>();
	static ArrayList<Integer> arr = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]>max) max = map[i][j]; //입력받을떄 max값 저장
			}
		}
		for (int h = 0; h <= max; h++) {
			visited = new boolean[N][N]; //비오는 양 별로 3중for문 돌때마다 초기화
			cnt = 0;
			for (int i = 0; i <N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]&&map[i][j]>h) {
						bfs(new Node(i,j),h);
						cnt++; //영역체크
					}
				}
			}
			arr.add(cnt);
		}
		//arraylist에서 최댓값 바로 뽑을수있움!
		System.out.println(Collections.max(arr));

	}
	private static void bfs(Node node, int h) {
		visited[node.x][node.y] = true; //방문체크
		que.add(node);
		while(!que.isEmpty()) {
			Node current = que.poll(); //하나씩 꺼내서
			int r = current.x;
			int c = current.y;
			for (int d = 0; d < 4; d++) { //4방 탐색
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr>=0&&nc>=0&&nr<N&&nc<N) { //범위안에있고
					if(!visited[nr][nc]&&map[nr][nc]>h) { //방문안했고 안잠기면
						visited[nr][nc] = true; //방문체크하고 
						que.add(new Node(nr,nc)); //bfs 하기 위해 큐에 넣어줌
					}
				}
			}
		}
		
	}

}
