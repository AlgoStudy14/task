import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_G5_16432_떡장수와호랑이 {
	/*
	 * <문제>
	 * 떡의 종류 1~9
	 * 전날 먹은떡과 같은 종류 떡이면 먹지 않음. 줄 수 있는 떡이 없다면 잡아먹힘.
	 * N일 동안 떡을 팔러 장터에 나가야하는데, 재료 공급에 따라 동희의 떡 종류는 달라짐
	 * N일동안 먹히지 않도록 줄 떡 고르기.
	 * 
	 * <문제풀이>
	 * 순열? dfs.
	 * 오랜만에 dfs,.... 
	 */
	static int N;
	static ArrayList<Integer>[] dduk;
	static boolean[][] visit;
	static int[] ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = new int[N + 1];
		visit = new boolean[N + 2][10];
		dduk = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			dduk[i] = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			int n = sc.nextInt();
			for(int j = 0; j < n; j++)
				dduk[i].add(sc.nextInt());
		}
		
		dfs(1, 0);
		System.out.println("-1");
	}	
	
	static void dfs(int idx, int s) {
		if(idx == N + 1) {
			for(int i = 1; i <= N; i++)
				System.out.println(ans[i]);
			System.exit(0);
		}
		
		for(int i = 1; i < 10; i++) {
			if(i != s && !visit[idx + 1][i] && dduk[idx].contains(i)) {
				visit[idx + 1][i] = true;
				ans[idx] = i;
				dfs(idx + 1, i);
			}
		}
	}
}
