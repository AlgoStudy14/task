package algo210703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver2_6603_로또 {

	static int input[], comb[], N;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0) {
				return;
			}

			input = new int[N];
			comb = new int[6];
			
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}			
			
			combination(0, 0);
			
			System.out.println();
		}
	}
	
	private static void combination(int idx, int start) {
		
		if(idx == 6) {
			printComb();
			return;
		}
		
		for (int i = start; i < N; i++) {
			comb[idx] = input[i];
			combination(idx+1, i+1);
		}
	}

	private static void printComb() {
		for (int i = 0; i < 6; i++) {
			System.out.print(comb[i] + " ");
		}
		System.out.println();
		
	}
}
