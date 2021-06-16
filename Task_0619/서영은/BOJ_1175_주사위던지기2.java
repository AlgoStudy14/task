package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1175_주사위던지기2 {

	static int N, M, answer[];
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		answer = new int[N];
		
		find(0, 0);
	}
	
	private static void find(int idx, int sum) {
		
		if(idx == N) {
			if(sum == M) printAnswer();
			return;
		}
		
		for (int curNum = 1; curNum <= 6; curNum++) {
			answer[idx] = curNum;
			find(idx+1, sum+curNum);
		}
	}
	
	private static void printAnswer() {
		for (int i = 0; i < N; i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.println();
	}

}
