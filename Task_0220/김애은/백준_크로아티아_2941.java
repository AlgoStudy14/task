package algo_myselr;

import java.util.Scanner;

public class BOJ_2941_실버5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cr[] = {"c=", "c-", "dz=", "d-", "lj", "nj","s=", "z="};
		String res=sc.next();
		for(int i=0;i<cr.length;i++) {
			if(res.contains(cr[i])) {
				res=res.replace(cr[i], "q");
			}
		}
		System.out.println(res.length());
	}		
}
