package algo0702;

import java.util.Scanner;

/**
 * <문제 요약>
 * 문제 정의 : S와 K가 주어졌을 때, 수를 고르는 모든 방법을 출력(사전 순)
 * 문제 유형 : 조합
 * 주의 사항 : 
 * <풀이 요약>
 * k는 최대 12이므로 최대 조합 가능
 */

public class BOJ_6603_로또 {

	static int k;
	static int[] S, arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		arr = new int[6];
		
		while (k != 0) {
			S = new int[k];
		
			for (int i = 0; i < k; i++)
				S[i] = sc.nextInt();
			
			combination(0, 0);
			System.out.println();
			
			k = sc.nextInt();
		}
	}

	static void combination(int depth, int start) {
		if (depth == 6) {
			print();
			return;
		}
		
		for (int i = start; i < k; i++) {
			arr[depth] = S[i];
			combination(depth + 1, i + 1);
		}
	}
	
	static void print() {
		for (int i = 0; i < 6; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
//924