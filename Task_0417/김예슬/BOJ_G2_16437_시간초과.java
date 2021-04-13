package algo0412;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 1번 섬에 도달할 수 있는 양의 최대값
 * 문제 유형 : DFS를 사용
 * <풀이법 요약>
 * 0. island: 섬의 정보 저장
 * 1. 1번 섬부터 탐색 시작
 * 2. 각 섬마다, 양일 경우, 1번까지 갈 수 있는 양의 수
 *  2-1. 양이라면 amount를 더함
 *  2-2. 늑대라면 amount를 뺌
 * -> 갔던 곳도 다시 방문하면서 시간초과
 */

public class BOJ_G2_16437_시간초과 {

	static int N;
	static long max;
	static ArrayList<Island> island;
	
	static class Island {
		boolean isSheep;
		int amount, to;
		
		public Island(boolean isSheep, int amount, int to) {
			this.isSheep = isSheep;
			this.amount = amount;
			this.to = to;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		island = new ArrayList<>();
		
		island.add(new Island(true, 0, 0));
		for (int i = 0; i < N - 1; i++) {
			boolean isSheep = sc.next().equals("S") ? true : false;
			island.add(new Island(isSheep, sc.nextInt(), sc.nextInt()));
		}
		
		solve();
		
		System.out.println(max);
	}
	
	static void solve() {
		for (int i = 1; i < island.size(); i++) {
			Island cur = island.get(i);
			
			if (cur.isSheep) {
				int amount = cur.amount;
				do {
					if (!cur.isSheep) {
						amount -= cur.amount;
						if (amount <= 0) {
							cur.amount = -amount;
							break;
						} else cur.amount = 0;
					}
					
					if (cur.to == 1) max += amount;
					
					Island next = island.get(cur.to - 1);
					cur = next;
				} while (cur.to != 0);				
			}
		}
	}
}
/*
7
W 10 1
W 40 2
W 10 2
S 100 3
S 50 3
S 20 4
110
*/
