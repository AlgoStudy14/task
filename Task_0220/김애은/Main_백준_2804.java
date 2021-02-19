package algo_myselr;

import java.util.Scanner;

public class BOJ_2804_브론즈2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a=sc.next();
		String b=sc.next();
		int size=a.length();
		int samea=0,sameb=0;
		if(a.length()>b.length())
			size = b.length();
		for(int i=0;i<size;i++) {
			if(b.indexOf(a.charAt(i))!=-1) {
				sameb=b.indexOf(a.charAt(i));
				samea=i;
				break;
			}
		}
		for(int i=0;i<b.length();i++) {
			for(int j=0;j<a.length();j++) {
				if(j==samea) {
					System.out.print(b.charAt(i));
				}
				else if(i==sameb)
					System.out.print(a.charAt(j));
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}

}
