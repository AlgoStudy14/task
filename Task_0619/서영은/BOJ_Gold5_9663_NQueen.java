package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * <문제 요약>
 * 구해야 하는 것:퀸을 놓는 방법의 수 
 * 제약 사항: 1<=N<15
 * 문제 유형: 백트래킹
 * 요구 개념: 백트래
 * 
 * <풀이법 요약>
 * 1. 모든 경우를 탐색하며 가지치기하기
 * 	-> 가로, 세로, 대각선인 경우 제외
 * 2. 체스판은 일차원 배열로 설정(내용물 = 놓여있는 열의 idx를 의미)
 */
public class BOJ_Gold5_9663_NQueen {
	
	static int N, chess[], cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		chess = new int[N];
		Arrays.fill(chess, -1);
		
		queen(0);
		
		System.out.println(cnt);
	}

	private static void queen(int depth) {
		if(depth == N) {
			++cnt;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if(!checkCol(depth, c) || !checkX(depth, c)) continue;
			
			chess[depth] = c;
			queen(depth+1);
			chess[depth] = -1;
		}
	}

	private static boolean checkX(int depth, int c) {
		for(int r=0; r<depth; r++) {
			if(Math.abs(r-depth) == Math.abs(chess[r] - c)) return false;
		}
		return true;
	}

	private static boolean checkCol(int depth, int c) {
		for(int r=0; r<depth; r++) {
			if(chess[r] == c) return false;
		}
		return true;
	}

}
