package algo0601;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 폭발한 구슬(1, 2, 3번)의 개수 출력
 * 제약 사항 : N은 항상 홀수
 * 문제 유형 : 시뮬레이션
 * <풀이법 요약>
 * 1. throwFrag(d, s) - d방향으로 s거리만큼 얼음파편 던져 구슬파괴
 * 2. move() 		  - 구슬이 파괴된 빈칸으로 이동
 * 3. explode() 	  - 4개 이상 연속하는 구슬이 있을 때 폭발
 * 4. convert() 	  - 폭발할 구슬이 없으면 A와 B로 변환
 */

public class BOJ_G2_21611_마법사상어와블리자드 {
	
	static int N, M, sum;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static ArrayList<Integer> marble;
	static Deque<Integer> aandb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		marble = new ArrayList<>();
		aandb = new LinkedList<>();
		
		for (int i = 0; i < N * N; i++)
			map[i / N][i % N] = sc.nextInt();
		
		for (int i = 0; i < M; i++) {
			int d = sc.nextInt() - 1;
			int s = sc.nextInt();
			throwFrag(d, s);
			move();
			explode();
			convert();
			tomap();
		}
		
		System.out.println(sum);
	}
	
	// 1. d방향으로 s거리만큼 얼음파편 던지기
	static void throwFrag(int d, int s) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int start = N / 2;
		
		for (int i = 1; i <= s; i++) {
			int nx = start + i * dx[d];
			int ny = start + i * dy[d];
			map[nx][ny] = 0;
		}
	}
	
	// 2. 구슬이 파괴된 빈칸으로 이동
	static void move() {
		marble.clear();
		int dir = 0;
		int size = 1;
		int start = N / 2;
		int nx = start;
		int ny = start;
		
		for (int i = 1; i < N * 2; i++) {
			for (int s = 1; s <= size; s++) {
				nx += dx[dir];
				ny += dy[dir];
				
				if (map[nx][ny] == 0) continue;
				
				marble.add(map[nx][ny]);
			}
			
			dir = (dir + 1) % 4;
			// 2번씩 크기가 바뀜
			if (i % 2 == 0 && i != (N - 1) * 2) ++size;
		}
	}
	
	// 3. 폭발
	static void explode() {
		boolean exploded = false;
		int sameCnt = 1;
		int idx = 0;
		
		while (true) {
			if (marble.get(idx) == marble.get(idx + 1)) {
				++sameCnt;
			} else {
				if (sameCnt >= 4) {
					sum += marble.get(idx) * sameCnt;
					for (int i = 0; i < sameCnt; i++) {
						marble.remove(idx - sameCnt + 1);
					}
					idx -= sameCnt;
					exploded = true;
				}
				sameCnt = 1;
			}
			if (++idx > marble.size() - 2) break;
		}
		
		// 만약 구슬이 파괴됐다면 다시 4개 이상 연속하는 구슬이 있는지 체크해야됨
		if (exploded) explode();
	}
	
	// 4. A와 B로 변환
	static void convert() {
		aandb.clear();
		
		int idx = 0;
		int size = marble.size();
		int b = 1;
		
		while (true && idx < size) {
			int a = marble.get(idx++);
			while (idx < size && a == marble.get(idx)) {
				++b;
				++idx;
			}
			
			if (aandb.size() < N * N) aandb.add(b);
			if (aandb.size() < N * N) aandb.add(a);
			b = 1;
		}
	}
	
	// 블리자드를 한 결과(ArrayList aandb)를 map에
	static void tomap() {
		map = new int[N][N];
		int dir = 0;
		int size = 1;
		int start = N / 2;
		int nx = start;
		int ny = start;
		
		for (int i = 1; i < N * 2; i++) {
			for (int s = 1; s <= size; s++) {
				nx += dx[dir];
				ny += dy[dir];
				
				map[nx][ny] = aandb.pollFirst();
				
				if (aandb.isEmpty()) return;
			}
			
			dir = (dir + 1) % 4;
			if (i % 2 == 0 && i != (N - 1) * 2) ++size;
		}
	}
}
