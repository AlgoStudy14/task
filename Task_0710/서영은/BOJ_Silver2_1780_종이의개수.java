package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_Silver2_1780_종이의개수 {

	static int N, paper[][], cnt[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		paper = new int[N][N];
		cnt = new int[4];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < N; c++) {
				paper[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		func(N, 0, 0);
		
		for (int i = 0; i < 3; i++) {
			bw.write(cnt[i] + "\n");
		}
		
		bw.flush();
		in.close();
		bw.close();
	}
	
	private static void func(int n, int startR, int startC) {
		int checkParam = checkPaper(n, startR, startC);
		
		if(checkParam == -1 || checkParam == 0 || checkParam == 1) {
			++cnt[checkParam+1];
			return;
		}
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				func(n/3, startR + r*n/3, startC + c*n/3);
			}
		}
	}

	private static int checkPaper(int n, int startR, int startC) {
		int startNum = paper[startR][startC];
		
		for (int r = startR; r < startR + n; r++) {
			for (int c = startC; c < startC + n; c++) {
				if(paper[r][c] != startNum) return -2;
			}
		}
		
		return startNum;
	}
}
