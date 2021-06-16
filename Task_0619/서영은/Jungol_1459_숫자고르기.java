package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jungol_1459_숫자고르기 {

	static int N, maxCnt, first[], second[], answer[];
	static List<Integer> selectedFirst, selectedSecond;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		first = new int[N];
		second = new int[N];
		selectedFirst = new ArrayList<Integer>();
		selectedSecond = new ArrayList<Integer>();
		answer = new int[N];
		maxCnt = -1;
		
		for (int i = 0; i < N; i++) {
			first[i] = i+1;
			second[i] = Integer.parseInt(in.readLine());
		}
		
		
		find(0, 0);

		System.out.println(maxCnt);
		
		for (int i = 0; i < maxCnt; i++) {
			System.out.println(answer[i]);
		}
	}

	private static void find(int idx, int cnt) {
		
		if(idx == N) {
			Collections.sort(selectedSecond);
			
			for (int i = 0; i < cnt; i++) {
				if(selectedFirst.get(i) != selectedSecond.get(i)) return;
			}
			
			if(cnt > maxCnt) {
				maxCnt = cnt;
				for (int i = 0; i < maxCnt; i++) {
					answer[i] = selectedFirst.get(i);
				}
			}
			return;
		}
		
		selectedFirst.add((Integer) first[idx]);
		selectedSecond.add((Integer) second[idx]);
		find(idx+1, cnt+1);
		
		selectedFirst.remove((Integer) first[idx]);
		selectedSecond.remove((Integer) second[idx]);
		find(idx+1, cnt);
	}
}
