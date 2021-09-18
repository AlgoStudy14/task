package algo0918;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * <문제 요약>
 * 문제 정의: 종료되었을 때 몇 번째 단계가 진행 중이었는지를 출력
 * 문제 유형: 시뮬레이션
 * <풀이 요약>
 * 1. 벨트가 로봇과 함께 한 칸 회전
 * 2. 가장 먼저 올라간 로봇부터 한 칸 이동
 *  2-1. 이동하려는 칸에 로봇이 존재한다면
 *  2-1. (로봇X && 내구도 >= 1)이면 이동 가능
 * 3. 올리는 위치의 칸이 내구도가 0이 아니면 로봇을 올림
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 종료
*/

public class BOJ_S1_20055_컨베이어벨트위의로봇 {

	static int N, K;
	static ArrayList<Robot> Belt;
	
	static class Robot {
		int d;
		boolean exist;
		
		public Robot(int d, boolean exist) {
			this.d = d;
			this.exist = exist;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		Belt = new ArrayList<>();
		
		for (int i = 0; i < 2 * N; i++) {
			Belt.add(new Robot(sc.nextInt(), false));
		}
		
		System.out.println(solve());
	}

	static int solve() {
		int step = 1;
		while (true) {
			rotate();
			move();
			insert();
			if (check() >= K) return step;
			++step;
		}
	}
	
	// 1. 벨트 & 로봇 회전
	static void rotate() {
		// 컨베이어의 마지막 칸에 있는 벨트를 처음으로 이동 
		Belt.add(0, Belt.remove(Belt.size() - 1));
		remove();
	}
	
	// 2. 로봇 한 칸 이동(i -> i+1)
	static void move() {
		for (int i = N - 2; i >= 0; i--) {
			// 2-1. i칸에 로봇이 존재하지 않다면
			if (!Belt.get(i).exist) continue;
			
			// 2-1. i칸에 로봇이 존재한다면
			// 2-2. (i + 1)칸의 내구도가 0보다 작거나,
			// 		(i + 1)칸에 로봇이 존재하면 이동 불가능
			if (Belt.get(i + 1).exist || Belt.get(i + 1).d == 0) continue;
						
			// 2-2. (i + 1)칸의 내구도가 0보다 크고,
			//		(i + 1)칸에 로봇이 존재하지 않다면 이동 가능
			--Belt.get(i + 1).d;
			Belt.get(i + 1).exist = true;
			Belt.get(i).exist = false;
		}
		remove();
	}
	
	// 3. 올리는 위치에 로봇 올림
	static void insert() {
		// 올리는 위치의 내구도가 0보다 작다면 패스
		if (Belt.get(0).d == 0) return;
		
		--Belt.get(0).d;
		Belt.get(0).exist = true;;
	}
	
	// 4. 내구도가 0인 칸의 개수를 카운팅
	static int check() {
		int cnt = 0;
		for (Robot r : Belt) {
			if (r.d == 0) ++cnt;
		}
		return cnt;
	}
	
	// 1 or 2. 로봇 내리기
	static void remove() {
		Belt.get(N - 1).exist = false;
	}
}
