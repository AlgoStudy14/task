package imprepare.problems;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2804_크로스워드만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		int N=s1.length();
		int M=s2.length();
		char[][] ans = new char[M][N];
		//처음에 이거 Arrays.fill(ans,'.'); 해서 채우기 오류
		for (int i = 0; i < M; i++) {
			Arrays.fill(ans[i], '.');
		}
		//3중 for문 따위 두렵지 않아
		a:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//처음 둘이 같은 알파벳 만나는 곳 i,j
				if(s1.charAt(i)==s2.charAt(j)) {
					//열 바꿔가면서 출력
					for (int n = 0; n < N; n++) {
						ans[j][n] = s1.charAt(n);
					}
					//행 바꿔가면서 출력
					for (int m = 0; m < M; m++) {
						ans[m][i] = s2.charAt(m);
					}
					//for문 전체 탈출!
					break a;
				}
				
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
//				if(ans[i][j]!='.') System.out.print(ans[i][j]);
//				else System.out.print('.');
				System.out.print(ans[i][j]);
				
			}
			System.out.println();
		}

	}

}
