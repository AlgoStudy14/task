import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S1_전쟁전투 {
	/*
	 * <문제>
	 * 연속으로 이어진 병사들 수 ^ 2 해서 더하여 W B 순으로 출력하기.
	 * 
	 * <문제풀이>
	 * BFS. 저장할때 W는 1로, B는 0으로 저장하여 int형에 저장시킨다.
	 * W를 만났을 때, 사방탐색을 통해 이어가며 count를 더함. 이 count^2 + 끝까지 탐색했을 때 W 수 ^ 2를 더함.
	 * B도 마찬가지로 2번을 해서
	 * 배열[1]에 W, [0] 에 B를 저장하여 출력하기.
	 * 
	 * <문제점>
	 * 1. 18퍼에서 런타임에러(StringIndexOutOfBound)가 발생. String문제면 값 입력받을 때 생기는 문제 같은데...	=> M, N이 반대였네 ㅡㅡ
	 */
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] result;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		result = new int[2];
		String s = "";
		for(int i = 0; i < N; i++) {
			s = sc.next();
			for(int j = 0; j < M; j++) {
				if(s.charAt(j) == 'W')
					map[i][j] = 1;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j])
					bfs(i, j, map[i][j]);
				else if(map[i][j] == 0 && !visited[i][j])
					bfs(i, j, map[i][j]);
			}
		}
		System.out.println(result[1] + " " + result[0]);
	}
	
	static void bfs(int i, int j, int v) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int count = 1;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == v && !visited[nr][nc]) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					count++;
				}
			}
		}
		result[v] += (count * count);
	}
}