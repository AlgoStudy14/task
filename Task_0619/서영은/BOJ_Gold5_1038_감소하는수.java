package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_Gold5_1038_감소하는수 {

	static int N;
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		if(N<=10) {
			System.out.println(N);
			return;
		}
		if(N>1022) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i <= 9; i++) {
			func(i, 1);
		}
		
		Collections.sort(list);
		
		System.out.println(list.get(N));

	}

	private static void func(int num, int cnt) {
		if(cnt > 10) {
			return;
		}
		list.add(num);
		
		for (int mod = 0; mod < num%10; mod++) {
			func(num*10 + mod, cnt+1);
		}
	}

}
