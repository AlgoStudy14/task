package algo0405;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 치킨집을 최대 M개 골랐을 때, 도시의 치킨거리의 최솟값을 출력
 * 제약 사항 : 두 좌표 사이의 거리는 맨해튼 거리
 * 문제 유형 : 완탐, 조합을 사용
 * <풀이법 요약>
 * 1. 치킨집과 집을 ArrayList에 담음
 * 2. 최대 M개의 치킨집을 선택(조합 - M은 최대 13이기 때문에 가능)
 *  2-1. 각 집과 선택한 치킨집들과의 거리를 계산
 *  2-2. 그 중 최소 거리를 구함
 */

public class BOJ_G5_15687 {

	static int N, M, min;
	static int[] select;
	static ArrayList<Pos> chicken, home;
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		select = new int[M];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for (int i = 0; i < N * N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = sc.nextInt();
				
				// 치킨집과 집은 따로 배열에 담음
				if (tmp == 1) home.add(new Pos(i, j));
				if (tmp == 2) chicken.add(new Pos(i, j));
			}
		}
		
		min = Integer.MAX_VALUE;
		select(0, 0);
		
		System.out.println(min);
	}

	// M개의 치킨집을 선택
	static void select(int depth, int start) {
		if (depth == M) {
			min = Math.min(min, getDistance());
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			select[depth] = i;
			select(depth + 1, i + 1);
		}
	}
	
	// M개의 치킨집을 선택했을 때, 선택한 치킨집들과 집의 거리 중 최소값을 더함
	static int getDistance() {
		int sum = 0;
		for (int h = 0; h < home.size(); h++) {			// 각 집마다
			int d = 102;
			
			for (int c = 0; c < M; c++) {				// 선택한 치킨집과의 거리를 구함
				Pos cur = chicken.get(select[c]);
				int tmp = Math.abs(cur.x - home.get(h).x) + Math.abs(cur.y - home.get(h).y);
				d = Math.min(d, tmp);
			}
			
			sum += d;
		}
		return sum;
	}
}
