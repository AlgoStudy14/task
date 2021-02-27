package algo_myselr;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solution_1234 {
	static int X;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=1;i<=10;i++) {
			Stack<Integer> st = new Stack<>();
			Stack<Integer> re = new Stack<>();
			String res="";
			int size=sc.nextInt();
			String s = sc.next();
			String q[]=s.split("");
			st.add(Integer.parseInt(q[0]));
			for(int k=1;k<q.length;k++) {
				if(!st.empty()) {
					if (st.peek() == Integer.parseInt(q[k])) {
						st.pop();
					} else {
						st.add(Integer.parseInt(q[k]));
					}
				}else
					st.add(Integer.parseInt(q[k]));
			}
			int len = st.size();
			for(int k=0;k<len;k++) {
				re.add(st.pop());
			}
			for(int k=0;k<len;k++) {
				System.out.print(re.pop());
			}
			System.out.println();

		}
	}
}
