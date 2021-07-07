package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver1_11048_이동하기_dp {

	static int N, M, map[][], dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.write(process()+"\n");
		
		in.close();
		bw.flush();
		bw.close();
		
	}
	
	private static int process() {
		
		int maxNum = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				map[r][c] = map[r][c] + Math.max(map[r-1][c], Math.max(map[r-1][c-1], map[r][c-1]));
			}
		}
		
		return map[N][M];
	}
	
	private static boolean checkBoundary(int r, int c) {
		return r>=1 && r<=N && c>=1 && c<=M;
	}
}
