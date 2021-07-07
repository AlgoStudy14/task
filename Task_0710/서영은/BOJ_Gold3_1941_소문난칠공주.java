package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_Gold3_1941_소문난칠공주 {

	static char students[][];
	static int allCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		students = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String student = in.readLine();
			for (int j = 0; j < 5; j++) {
				students[i][j] = student.charAt(j);
			}
		}
		
		//logic
		
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				if(students[r][c] == 'Y') {
					findGongju(r, c, 0, 1, 1);
				} else if(students[r][c] == 'S'){
					findGongju(r, c, 1, 0, 1);
				}
			}
		}
		
		bw.write(allCnt + "\n");
		
		in.close();
		bw.flush();
		bw.close();
	}

	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};
	private static void findGongju(int r, int c, int cntS, int cntY, int cnt) {
		if(cntY >= 4) return;
		if(cnt == 7) {
			if(cntS >= 4) ++allCnt;
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc)) continue;
			
			if(students[nr][nc] == 'Y') {
				findGongju(nr, nc, cntS, cntY+1, cnt+1);
			} else if(students[nr][nc] == 'S'){
				findGongju(nr, nc, cntS+1, cntY, cnt+1);
			}
		}
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<5;
	}

}
