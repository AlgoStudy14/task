package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_SILVER3_11399 {
	/*
	 * ATM
	 * 
	 * 정렬해서 더하면될듯?
	 */
	static int N;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= i; j++)
				count += arr[j];
		}
		System.out.println(count);
	}
}