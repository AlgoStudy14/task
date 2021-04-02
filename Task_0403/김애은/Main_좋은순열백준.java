package algo0402;

import java.util.Scanner;

public class Main_좋은순열백준 {
	static int N;//길이
	static boolean end;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		back("1");
		
		
	}
	private static void back(String str) {
		if(end)return;
		if(str.length()==N) {
			System.out.println(str);
			end=true;
			return;
		}
		for(int i=1;i<=3;i++) {
			if(able(str+i))
				back(str+i);
		}
	}
	private static boolean able(String str) {
		int len=str.length();
		for(int i=1;i<=len/2;i++) {
			String f=str.substring(len-i-i,len-i);//앞부분 
			String e = str.substring(len-i,len);//뒷부분
			
			if(f.equals(e))return false;
		}
		return true;
	}

}
