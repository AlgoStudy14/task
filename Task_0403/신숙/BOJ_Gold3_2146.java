import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_Gold3_2146 {
	/*
	 * <https://www.youtube.com/user/yongjunful/featured> <= B반 교수라고 되어있는 거보니 SSAFY 교수님 중 한분이신듯.
	 * 
	 * N이 주어지면, [N][N]의 map이 주어지고, 
	 * 
	 * 1은 육지, 0은 바다. 1들이 연결되고 바다에 갇히면 이것이 대륙.
	 * 2개의 대륙을 연결하고자 할때 가장 짧은 다리는?
	 * 
	 * 이거 저번에 못푼 거랑 비슷. 넘버링? 라벨링? 하고 푸는 거라고 봤었는데.
	 * 		=> 라벨링 후, 모든 섬(사방탐색 DFS?)에서 BFS실시하여서.
	 * 
	 * 1. 섬간의 구별이 필요.
	 * 2. 섬의 각 지점에서 다른 섬으로 탐색
	 */
	static class Point{
		int r, c, d, idx;
		Point(int r, int c, int d, int idx){
			this.r = r;		//x좌표
			this.c = c;		//y좌표
			this.d = d;		//다리의 길이
			this.idx = idx;	//섬 index
		}
	}
	static int N, ans, islandIdx;
	static int[][] map;
	static boolean[][] visited;
	
	static List<Point> points;	//육지 포인트를 넣어둘 리스트 => BFS 이용.
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		ans = Integer.MAX_VALUE;
		//map 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}
		//1. 각 섬 연결하기. 1이 육지이므로 2번부터 index 새기기
		// 발견한 점들을 통해 BFS를 할 예정이니 List에 넣기.
		points = new LinkedList<>();
		islandIdx = 2;
		//라벨링?
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 1) {
					bfsMarkIslandNum(r,c);
					islandIdx++;
				}
			}
		}
		//2. 섬의 각 지점에서 다른 섬까지 탐색하며 최저거리의 다리길이 구하기
		for(Point p : points)
			bfsGetShortBridgeLength(p);
		System.out.println(ans);
	}
	//라벨링 해주는 함수
	static void bfsMarkIslandNum(int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		Point p = new Point(r, c, 0, islandIdx);
		q.offer(p);
		map[r][c] = islandIdx;
		
		points.add(p);
		while(!q.isEmpty()) {
			Point front = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = front.r + dr[d];
				int nc = front.c + dc[d];
				//범위 내에 있다면 => 같은 대륙. 라벨링 해주고 다음 값 queue에 넣어서 bfs
				if(isIn(nr, nc)) {
					if(map[nr][nc] == 1) {
						map[nr][nc] = islandIdx;
						p = new Point(nr, nc, 0, islandIdx);
						q.offer(p);
						points.add(p);
					}
				}
			}
		}
	}
	//짧은 다리 찾는 함수
	static void bfsGetShortBridgeLength(Point p) {
		Queue<Point> q = new LinkedList<>();
		q.offer(p);
		visited = new boolean[N][N];
		
		while(!q.isEmpty()) {
			Point front = q.poll();
			//저장된 거리가 이미 ans보다 크면 볼 필요 없음.
			if(front.d >= ans)
				break;
			
			for(int d = 0; d < 4; d++) {
				int nr = front.r + dr[d];
				int nc = front.c + dc[d];
				//범위 내고, 방문하지않았다면 실행
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					//대륙 안쪽으로 가면 안됨.
					if(map[nr][nc] == front.idx)
						continue;
					//바다일 때는 다리를 놓고 길이를 늘려야 함.
					else if(map[nr][nc] == 0)
						q.offer(new Point(nr, nc, front.d + 1, front.idx));
					//다른 대륙을 만났을 때. 거리를 비교해서 최소값 넣기.
					else if(map[nr][nc] != front.idx) {
						ans = Math.min(ans, front.d);
						return;
					}
				}
			}
		}
	}
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && c >= 0 && c < N;
	}
}