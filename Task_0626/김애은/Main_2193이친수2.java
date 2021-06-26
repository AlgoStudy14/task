package study0626;

import java.util.Scanner;

public class Main_2193이친수2 {
	static int cnt,N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		go(1,1);
		System.out.println(cnt);
	}
	private static void go(int cur, int c) {
		if(c==N) {
			cnt+=1;
			return;
		}
		if(cur==0) 
			go(1,c+1);
		go(0,c+1);	
	}

}
