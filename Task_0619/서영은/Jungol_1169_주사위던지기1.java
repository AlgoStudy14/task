package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1169_주사위던지기1 {

	static int N, M, answer[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		answer = new int[N];
		
		if(M==1) {
			first(0);
		} else if(M==2) {
			second(0, 1);
		} else if(M==3) {
			third(0);
		}
	}
	
	private static void first(int idx) {
		if(idx == N) {
			printAnswer();
			return;
		}
		
		for (int curNum = 1; curNum <= 6; curNum++) {
			answer[idx] = curNum;
			first(idx+1);
		}
	}
	
	private static void second(int idx, int start) {
		if(idx == N) {
			printAnswer();
			return;
		}
		
		for (int curNum = start; curNum <= 6; curNum++) {
			answer[idx] = curNum;
			second(idx+1, curNum);
		}
	}
	
	private static void third(int idx) {
		if(idx == N) {
			printAnswer();
			return;
		}
		
		for (int curNum = 1; curNum <= 6; curNum++) {
			if((idx != 0 && check(idx, curNum)) || idx == 0) {
				answer[idx] = curNum;
				third(idx+1);
			}
		}
	}

	private static boolean check(int idx, int curNum) {
		for (int i = 0; i < idx; i++) {
			if(answer[i] == curNum) return false;
		}
		return true;
	}

	private static void printAnswer() {
		for (int i = 0; i < N; i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.println();
	}
}
