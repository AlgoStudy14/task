import java.util.Scanner;

public class BOJ_Gold4_2458 {
	/*
	 * <키 순서>
	 * 1번 ~ N번 번호가 붙여진 학생들에 대해 키를 비교한 결과가 있음. 키는 모두 다름.
	 * 	ex) Line1 : 1 5	=> 1번 학생보다 5번이 키가 큼.
	 * 이 그래프를 가지고 자신의 키가 몇번 째 인지 알 수 있는 학생은 몇명?
	 * 
	 * <풀이 방법>
	 * 그래프.....가 뎁스가 같은 애들이 있으면 안된다?
	 * 		=> 푸는 방법을 모르겠음... 구글 
	 * 
	 */
	static int N, M, cnt, ans;
	static boolean[][] g, rg;
	static boolean[] visited, rvisited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		g = new boolean[N + 1][N + 1];
		rg = new boolean[N + 1][N + 1];
		int a, b;
		//연결 g는 정방향, rg는 역방향.
		for(int i = 0; i < M; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			g[a][b] = true;
			rg[b][a] = true;
		}
		//자신의 키를 정확히 아는 경우는 자기보다 큰 사람과 작은사람의 합이 자기를 뺀 N - 1인 경우.
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			rvisited = new boolean[N + 1];
			cnt = 0;
			dfs(i);			//키 큰사람
			rdfs(i);		//키 작은 사람
			if(cnt == N + 1)
				ans++;
		}
		System.out.println(ans);
	}
	
	static void dfs(int index) {
		cnt++;
		visited[index] = true;
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && g[index][i])
				dfs(i);
		}
	}
	
	static void rdfs(int index) {
		cnt++;
		rvisited[index] = true;
		for(int i = 1; i <= N; i++) {
			if(!rvisited[i] && rg[index][i])
				rdfs(i);
		}
	}
}