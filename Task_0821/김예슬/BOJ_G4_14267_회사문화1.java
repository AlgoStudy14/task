package algo0827;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : 각 직원들이 얼마의 칭찬을 받았는지 출력
 * 문제 유형 : dfs
 * <풀이 요약>
 * (상사의 번호 < 부하직원의 번호)이므로 칭찬의 정도는 위에서부터 누적된 값을 더해야 함
 * 밑에서부터 dfs를 할 경우, 점수가 누적되있는 상태가 아니므로 여러 번 반복해야 함
 * 0. 부하직원의 번호를 리스트로 저장
 * 1. 미리 칭찬의 정도를 누적
 * 2. 사장부터 dfs를 시작
 *  2-1. 부하직원의 칭찬 정도 + (상사~부하직원)까지의 누적된 칭찬의 정도
 */

public class BOJ_G4_14267_회사문화1 {

	static int N, M;
	static ArrayList<Integer>[] employees;
	static int[] scores;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		employees = new ArrayList[N + 1];
		scores = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			employees[i] = new ArrayList<>();
		}
		
		sc.nextInt();
		for (int i = 2; i <= N; i++) {
			employees[sc.nextInt()].add(i);
		}
		
		for (int i = 0; i < M; i++) {
			int who = sc.nextInt();
			int score = sc.nextInt();
			
			scores[who] += score;
		}
		
		dfs(1);
		print();
	}
	
	static void dfs(int me) {
		for (int sub : employees[me]) {
			scores[sub] += scores[me];
			dfs(sub);
		}
	}
	
	static void print() {
		for (int i = 1; i <= N; i++) {
			System.out.print(scores[i] + " ");
		}
	}
}
