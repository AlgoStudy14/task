package algo0417;

import java.util.Scanner;
/*
G2
U인 경우에는 (r-1, c)로 이동해야 한다.
R인 경우에는 (r, c+1)로 이동해야 한다.
D인 경우에는 (r+1, c)로 이동해야 한다.
L인 경우에는 (r, c-1)로 이동해야 한다.
미로에서 탈출 가능한 칸의 수를 계산해보자. 탈출 가능한 칸이란, 
그 칸에서 이동을 시작해서 칸에 적힌대로 이동했을 때, 미로의 경계 밖으로 이동하게 되는 칸을 의미한다.
 */
public class BOJ_17090_미로탈출하기 {

	static int N,M;
	static char[][] map;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
		}
		int cnt=0;
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < M; j++) {
				if(dfs(i,j)) cnt++;
			}
		}
		System.out.println(cnt);
	}
	private static boolean dfs(int r, int c) {
		boolean result = false;
		//범위 벗어나면 성공
		if(r<0||c<0||r>=N||c>=M) return true;
		//밖으로 나간 전적있는 경로-> 바로 return true;
		if(map[r][c]=='T') return true;
		//순환 전적 있는 경로 -> 바로 return false;
		else if(map[r][c] =='F') return false;
		//방문했던데 또 방문하면 순환: return false
		if(visit[r][c]) return false;
		//방문 체크
		visit[r][c] = true;
		
		if(map[r][c]=='D') result = dfs(r+1,c);
		else if(map[r][c]=='U') result = dfs(r-1,c);
		else if(map[r][c]=='R') result = dfs(r,c+1);
		else if(map[r][c]=='L') result = dfs(r,c-1);
		//어느 경로에서 왔던지 이미 밖으로 나간 전적이 있는 경로이거나 순환 전적있는 경로인지 체크해둠(T:F)
		//dfs를 한 점에서 갈수있는데까지 가보고 순환하면 지금까지 온 모든 경로에 F저장, 
		//밖에 나가면 T 저장
		map[r][c] = result?'T':'F';
		return result;
	}

}
