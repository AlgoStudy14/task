package algo0417;

import java.util.Arrays;
import java.util.Scanner;

/***
 * <문제 요약>
 * 구해야 하는 것 : 아인타팀이 얻을 수 있는 최대 점수 출력
 * 제약 사항: 1번 선수는 4번 타자, 각 이닝에는 아웃을 기록하는 타자 반드시 존재
 * 문제 유형 : 순열, dfs 사용(8P8)
 * <풀이법 요약>
 * 0. 
 * 1. 타자 순서 결정
 * 2. 순서 별 얻을 수 있는 점수 게산
 */

public class BOJ_G4_17281_야구2 {

	static int N, max;
	static int[] seq;
	static boolean[] ru;
	static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		seq = new int[9];
		arr = new int[N][9];
		
		for (int i = 0; i < N * 9; i++)
			arr[i / 9][i % 9] = sc.nextInt();
		
		seq[3] = 0;		// 1번 선수는 4번 타자
		select(0, 0);
		
		System.out.println(max);
	}

	static void select(int depth, int flag) {
		if (depth == 9) {
			max = Math.max(max, score());
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (i == 0) continue;
			if ((flag & 1<<i) != 0) continue;
			
			if (depth == 3) ++depth;
			seq[depth] = i;
			select(depth + 1, flag | 1<<i);
		}
	}
	
	static int score() {
		int score = 0;
		int batter = 0;
		ru = new boolean[3];
		
		for (int i = 0; i < N; i++) {
			int out = 0;
			Arrays.fill(ru, false);
			while (out < 3) {
				int cur = arr[i][seq[batter]];
				
				if (cur == 0) ++out;
				else score += hit(cur);
				
				batter = (batter + 1) % 9;
			}
		}
		return score;
	}
	
	static int hit(int cur) {
		int score = 0;
		switch(cur) {
		case 1:
			if (ru[2]) {
				++score;
				ru[2] = false;
			}
			if (ru[1]) {
				ru[2] = ru[1];
				ru[1] = false;
			}
			if (ru[0]) {
				ru[1] = ru[0];
				ru[0] = false;
			}
			ru[0] = true;
			break;
		case 2:
			if (ru[2]) {
				++score;
				ru[2] = false;
			}
			if (ru[1]) {
				++score;
				ru[1] = false;
			}
			if (ru[0]) {
				ru[2] = ru[0];
				ru[0] = false;
			}
			ru[1] = true;
			break;
		case 3:
			if (ru[2]) {
				++score;
				ru[2] = false;
			}
			if (ru[1]) {
				++score;
				ru[1] = false;
			}
			if (ru[0]) {
				++score;
				ru[0] = false;
			}
			ru[2] = true;
			break;
		case 4:
			if (ru[2]) {
				++score;
				ru[2] = false;
			}
			if (ru[1]) {
				++score;
				ru[1] = false;
			}
			if (ru[0]) {
				++score;
				ru[0] = false;
			}
			++score;
			break;
		}
		return score;
	}
}
