package algo0828;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 동희가 호랑이에게 N일동안 잡아먹히지 않도록 주어야 할 떡을 출력
 * 문제 유형 : dfs(순열)
 * <풀이 요약>
 * 1. 떡을 고를 수 있는 경우를 dfs로 저장
 *  1-1. 이전에 고른 떡과 같은 떡은 고를 수 없음
 *  1-2. n번째 날에 a떡을 줄 경우 불가능 했다면 다시 방문할 필요 없음
 * 2. 출력
 *  2-1. 가능하다면 N일 동안 줄 떡을 출력하고 종료
 *  2-1. 불가능 하다면 dfs() 다음인 출력구문을 만나 "-1"을 출력 
 * <피드백>
 * 시간초과..
 * [n번째 날][떡의 종류]을 체크
 * dfs를 하면서 방문한 적이 있을 경우 가지치기를 해줘야 함
 * 가지를 치지 않을 경우 대충 9^1000
 */

public class BOJ_16432_떡장수와호랑이 {

	static int N, M;
	static int[] ans;
	static ArrayList<TTEOK>[] T;

	static class TTEOK {
		int type;
		boolean able;
		
		public TTEOK(int type, boolean able) {
			this.type = type;
			this.able = able;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		ans = new int[N];
		T = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			M = sc.nextInt();
			T[i] = new ArrayList<>();
			
			for (int j = 0; j < M; j++) {
				T[i].add(new TTEOK(sc.nextInt(), true));
			}
		}
		
		dfs(0, 0);
		System.out.println(-1);
	}

	static void dfs(int depth, int before) {
		if (depth == N) {
			print();
			System.exit(0);
		}
		
		for (TTEOK t : T[depth]) {
			if (t.type == before) continue;
			if (!t.able) continue;
			
			t.able = false;
			ans[depth] = t.type;
			dfs(depth + 1, t.type);
		}
	}
	
	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(ans[i]);
		}
	}
}
