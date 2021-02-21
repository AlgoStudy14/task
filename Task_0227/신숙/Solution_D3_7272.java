package SW_Expert;

import java.util.Scanner;

public class Solution_D3_7272 {
	/*
	 * 문자열 두개를 비교해서 같은지 다른지 비교하는 문제.
	 * 하지만 문자열 자체를 비교하는 것이 아닌, 문자에 막혀있는 공간이 있는지의 개수로 비교함.
	 * Ex ) A = 1, B = 2, C = 0, D = 1, E = 0, F = 0, G = 0, H = 0, I = 0, J = 0, K = 0, L = 0, 
	 * M = 0, N = 0, O = 1, P = 1, Q = 1, R = 1, S = 0, T = 0, U = 0, V = 0, W = 0, X = 0, Y = 0, Z = 0
	 * 
	 * 배열로 alphabet의 0의 개수를 구해서 저장해두고, 문자열을 하나씩 비교할 건데, 'A'의 경우 int로 받아서, -'A'를 해주면 인덱스 번호가 될것.
	 */
	static int T;	
	static String a, b;
	static char[] left, right;
	static boolean flag;
	//					  a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
	static int[] alpha = {1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			a = sc.next();
			b = sc.next();
			flag = false;
			left = a.toCharArray();
			right = b.toCharArray();
			for(int i = 0; i < left.length; i++) {
				if(a.length() != b.length()) {
					flag= false;
					break;
				}
				if(alpha[left[i] - 'A'] != alpha[right[i] - 'A']) {
					flag = false;
					break;
				}
				flag = true;
			}
			if(!flag)
				System.out.println("#" + t + " DIFF");
			else
				System.out.println("#" + t + " SAME");
		}
	}
}