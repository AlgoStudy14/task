package imprepare.problems;

import java.util.Scanner;

public class BOJ_8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;
		
		for (int i = 1; i <=N/2+N%2; i++) {
			for (int j = i*i; j <=N; j+=i) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

}
