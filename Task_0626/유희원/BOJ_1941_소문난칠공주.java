package algo0626;

import java.util.Scanner;

public class BOJ_1941_소문난칠공주 {
	
	static int ans,cnt;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static char[][] array;
	static boolean[][] map;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		array = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < 5; j++) {
				array[i][j] = s.charAt(j);
			}
		}
		for (int i = 0; i <25; i++) {
			visited = new boolean[25];
			map = new boolean[5][5];
			dfs(i,1,0);
		}
		System.out.println(ans);

	}

	private static void dfs(int n, int cnt, int s) {
		if(array[n/5][n%5]=='S') ++s;
		visited[n] = true;
		map[n/5][n%5] = true;
		if(cnt==7) {
			//이다솜파 4명이상인 경우
			if(s>=4) find();
		}else {
			for (int i = n+1; i < 25; i++) {
				if(!visited[i]) dfs(i,cnt+1,s);
			}
		}
		visited[n] = false;
		map[n/5][n%5] = false;
	}

	private static void find() {
		for (int i = 0; i < 25; i++) {
			if(visited[i]) {
				int x = i/5;
				int y = i%5;
				boolean[][] visited = new boolean[5][5];
				visited[x][y] = true;
				cnt = 1;
				isComponent(x,y,visited);
				return;
			}
		}
		
	}

	private static void isComponent(int x, int y, boolean[][] checked) {
		if(cnt==7) ++ans;
		else {
			for (int d = 0; d <4; d++) {
				int nx = dr[d] + x;
				int ny = dc[d] + y;
				if(nx>=0&&ny>=0&&nx<5&&ny<5 && map[nx][ny] && !checked[nx][ny]) {
					checked[nx][ny] = true;
					++cnt;
					isComponent(nx, ny, checked);
				}
			}
		}
		
	}

}
