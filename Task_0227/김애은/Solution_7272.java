package algo_myselr;

import java.util.Scanner;

public class Solution_7272 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1;t<=T;t++) {
			boolean ch=true;
			String[] one = sc.next().split("");
			String[] two = sc.next().split("");
			if(one.length!=two.length) {
				System.out.println("#"+t+" DIFF");
				continue;
			}
			for(int i=0;i<one.length;i++) {
				if(!check(one[i],two[i])) {
					ch=false;
				}
			}
			if(ch)
				System.out.println("#"+t+" SAME");
			else
				System.out.println("#"+t+" DIFF");
		}

	}

	private static boolean check(String string, String string2) {
		String zero = "CEFGHIJKLMNSTUVWXYZ";
		String one = "ADOPQR";
		String three = "B";
		if(zero.contains(string) && zero.contains(string2))
			return true;
		if(one.contains(string) && one.contains(string2))
			return true;
		if(three.contains(string) && three.contains(string2))
			return true;
		return false;
	}

}
