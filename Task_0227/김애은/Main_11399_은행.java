package algo_myselr;

import java.util.Arrays;
import java.util.Scanner;

public class Main_11399_은행 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		int P[]= new int[N];
		
		for(int i=0;i<N;i++) {
			P[i]=sc.nextInt();
		}
		Arrays.sort(P);
		int temp=0;
		int sum=0;
		for(int i=0;i<N;i++) {
			temp+=P[i];
			sum+=temp;
		}
		System.out.println(sum);
	}
}
