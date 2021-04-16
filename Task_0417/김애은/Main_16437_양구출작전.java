package study0417;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main_16437_양구출작전 {
	static class island implements Comparable<island>{
		char type;
		int cnt, parent, cur;
		public island(char type, int cnt, int parent,int cur) {
			super();
			this.type = type;
			this.cnt = cnt;
			this.parent = parent;
			this.cur=cur;
		}
		public char getType() {
			return type;
		}
		public void setType(char type) {
			this.type = type;
		}
		public int getCnt() {
			return cnt;
		}
		public void setCnt(int cnt) {
			this.cnt = cnt;
		}
		public int getParent() {
			return parent;
		}
		public void setParent(int parent) {
			this.parent = parent;
		}
		public int getCur() {
			return cur;
		}
		public void setCur(int cur) {
			this.cur = cur;
		}
		@Override
		public int compareTo(island o) {
			return o.parent-this.parent;
		}
		
	}
	static int N,num;
	static ArrayList<island> is = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		is.add(new island('a',0,-1,0));
		for(int k=2;k<=N;k++) {
			char t = sc.next().charAt(0);
			int m = sc.nextInt();
			int p = sc.nextInt();
			is.add(new island(t,m,p-1,k));
		}
		Collections.sort(is);
		for(int i=0;i<is.size();i++) {
			go(i);
		}
		System.out.println(num);
	}
	private static void go(int i) {
		island cur = is.get(i);
		if(cur.parent==0) {
			
			if(cur.type=='S')
				num+=cur.getCnt();
			return;
		}
		int pa = cur.parent;
		
		if(cur.type=='S' && is.get(pa).type=='S') {
			is.get(pa).setCnt(is.get(pa).getCnt()+cur.cnt);
			System.out.println(is.get(pa).getCnt());
		}
		if(cur.type=='S' && is.get(pa).type=='W') {
			int sh = cur.cnt-is.get(pa).cnt;
			if(sh>0) {
				is.get(pa).setType('S');
				is.get(pa).setCnt(sh);
				
			}
		}
	}
}
