package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_Gold3_1941_소문난칠공주_sol {

	static char students[][];
	static int allCnt, comb[], combStudents[][];
	static boolean visitStudent[][];
	static List<int[]> list = new ArrayList<int[]>();
	static Queue<int[]> queue = new LinkedList<int[]>();

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		students = new char[5][5];
		comb = new int[7];
		for (int i = 0; i < 5; i++) {
			String student = in.readLine();
			for (int j = 0; j < 5; j++) {
				students[i][j] = student.charAt(j);
			}
		}
		
		//logic
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				list.add(new int[] {i,j});
			}
		}
		
		combination(0, 0, 0);
		
		bw.write(allCnt + "\n");
		
		in.close();
		bw.flush();
		bw.close();
	}

	private static void combination(int idx, int start, int cntY) {
		if(cntY >= 4) {
			return;
		}
		if(idx == 7) {
		
			if(checkAdj()) {
				++allCnt;
			}
			return;
		}
		
		for (int i = start; i < 25; i++) {
			comb[idx] = i;
			
			int r = list.get(i)[0];
			int c = list.get(i)[1];
			combination(idx+1, i+1, students[r][c]=='Y' ? cntY+1 : cntY);
		}
	}
	
	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};
	private static boolean checkAdj() {
		settingTempStudent();
		queue.clear();
		
		int r = list.get(comb[0])[0]; // 주의(인덱스)
		int c = list.get(comb[0])[1]; // 주의
		queue.offer(new int[] {r,c});
		visitStudent[r][c] = true; // 이거 넣기!
		
		int cnt = 1; // 이거 넣기!
		while(!queue.isEmpty()) {
			r = queue.peek()[0];
			c = queue.peek()[1];
			queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(!check(nr, nc) || combStudents[nr][nc] != 1 || visitStudent[nr][nc]) continue;
				
				visitStudent[nr][nc] = true;
				++cnt;
				queue.offer(new int[] {nr,nc});
			}
		};
		
		if(cnt == 7) return true;
		else return false;
	}

	private static void settingTempStudent() {
		combStudents = new int[5][5];
		visitStudent = new boolean[5][5];
		
		for (int idx = 0; idx < 7; idx++) {
			int listIdx = comb[idx];
			
			int r = list.get(listIdx)[0];
			int c = list.get(listIdx)[1];
			combStudents[r][c] = 1;
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<5;
	}
}
