package algo0903;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 지민이가 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 출력
 * 문제 유형 : 
 * <풀이 요약>
 * 사람 -> 참가하는 파티 번호
 */

public class BOJ_G4_1043_거짓말 {

	static int N, M, K;
	static int[] linked, knows;
	static List<Integer>[] party;
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 N = sc.nextInt();
		 M = sc.nextInt();
		 K = sc.nextInt();
		 linked = new int[N + 1];
		 knows = new int[K];
		 party = new ArrayList[M];
		 for (int i = 0; i < M; i++)
			 party[i] = new ArrayList<>();
		 
		 // 진실을 아는 사람에 대한 정보
		 for (int i = 0, k = 0; i < K; i++)
			knows[k++] = sc.nextInt();
		 		 
		 // 파티에 참여하는 사람들을 연결
		 make();
		 for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			
			for (int n = 0; n < num; n++)
				party[i].add(sc.nextInt());
			
			if (num > 1) unionSolve(i);
		}
		
		// 과장된 이야기를 할 수 있는 파티 개수
		int cnt = 0;
		for (int i = 0; i < M; i++)
			if (canLie(i)) ++cnt;
		System.out.println(cnt);
	}

	static boolean canLie(int cur) {
		for (int know : knows)
			if (findSet(know) == findSet(party[cur].get(0)))
				return false;
		return true;
	}
	
	static void unionSolve(int cur) {
		int first = party[cur].get(0);
		
		for (int i = 1; i < party[cur].size(); i++) {
			int tmp = party[cur].get(i);
			if (first < tmp) union(first, tmp);
			else union(tmp, first);
		}
	}
	
	static void make() {
		for (int i = 1; i <= N; i++)
			linked[i] = i;
	}
	
	static int findSet(int x) {
		if (linked[x] == x) return x;
		return linked[x] = findSet(linked[x]);
	}
	
	static void union(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		if (xRoot == yRoot) return;
		
		linked[yRoot] = xRoot;
	}
}