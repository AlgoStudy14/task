package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_15723_n단논법 {
	static List<Integer> list[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list=new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			list[i]=new ArrayList<>();
		}
		int n=Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String line=br.readLine();
			int start=line.charAt(0)-97;
			int last = line.charAt(line.length()-1)-97;
			list[start].add(last);
		}
		int m=Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			String check=br.readLine();
			int start=check.charAt(0)-97;
			int last = check.charAt(check.length()-1)-97;
			if(go(start,last))
				System.out.println("T");
			else
				System.out.println("F");
		}
	}
	private static boolean go(int start, int last) {
		Queue<Integer> que = new LinkedList();
		que.add(start);
		while(!que.isEmpty()) {
			int cur=que.poll();
			for(int next:list[cur]) {
				if(next==last) return true;
				que.add(next);
			}
		}
		return false;
	}

}
