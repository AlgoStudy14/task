package algo210417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 자신의 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지 출력
 * 제약 사항: 학생수 N -> 2<=N<=500, 0<=M<=N(N-1)/2
 * 문제 유형: 플루이드-와샬 알고리즘 
 * 요구 개념: 플루이드-와샬 알고리즘
 * 
 * <풀이법 요약>
 * 1. 먼저 작은 키 학생 -> 큰 키 학생 가는 것 인접 행렬에 표시
 * 2. 플루이드 와샬 알고리즘 진행
 * 3. 1~N까지 해당 정점부터 시작하는 최단경로, 해당 정점으로 도착하는 최단경로가 모든 정점이 있으면 몇 번째인지 알 수 있는 학생
 * 
 */
public class BOJ_Gold4_2458_키순서 {

	static int matrix[][], N, M, INF=100000000;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(matrix[i], INF);
			matrix[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			matrix[from-1][to-1] = 1;
		}
		
		System.out.println(process());
	}
	
	private static int process() {
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(k==i) continue;
				for (int j = 0; j < N; j++) {
					if(j==k || j==i) continue;
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
		
		return checkStudent();
	}

	private static int checkStudent() {
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			boolean[] check = new boolean[N];
			
			check[i] = true;
			
			//i가 시작정점일 때 
			for (int j = 0; j < N ; j++) {
				if(i == j) continue;
				if(matrix[i][j] != INF) {
					check[j] = true;
				}
			}
			
			//i가 도착 정점일 때
			for (int j = 0; j < N ; j++) {
				if(i == j) continue;
				if(matrix[j][i] != INF) {
					check[j] = true;
				}
			}
			
			boolean success = true;
			for (int j = 0; j < N; j++) {
				if(!check[j]) {
					success = false;
					break;
				}
			}
			
			if(success) ++count;
		}
		return count;
	}
}
