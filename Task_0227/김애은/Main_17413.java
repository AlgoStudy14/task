package algo_myselr;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main_17413 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s[] = sc.nextLine().split(" ");
		System.out.println(Arrays.toString(s));
		String t="";
		for(String sss : s) {
			if(!(sss.contains("<"))) {
				t+=change(sss);
			}
			else {
				t+=sub(sss);
			}
			t+=" ";
		}
		System.out.println(t);
	}

	private static String sub(String sss) {
		int start = sss.indexOf("<");
		int end = sss.indexOf(">");
		String first="";
		String fin="";
		String temp=sss.substring(start+1, end);
		if(start>0) {
			first = change(sss.substring(0,start));
		}
		if(end<sss.length()-1) {
			fin = change(sss.substring(end+1,0));
		}
		String res=first+temp+fin;
		if(res.contains("<")) {
			sub(res);
		}
		return res;
		
	}

	private static String change(String sss) {
		Stack<Character> st = new Stack<>();
		String temp="";
		for(int i=0;i<sss.length();i++) {
			temp+=sss.charAt(sss.length()-i-1);
		}
		System.out.println(temp);
		return temp;	
	}
}
