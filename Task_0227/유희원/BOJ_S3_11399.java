package imprepare.problems;

import java.util.Arrays;
import java.util.Scanner;

//기껏 넥퍼 짜고있었는데 통수 맞은 문젴ㅋㅋㅋㅋ
public class BOJ_11399_ATM2 {

	static int N,sum,ans;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
    //그냥 정렬해서
		Arrays.sort(arr);
		
    //순서대로 더해주면 그게 최솟값
		for (int i = 0; i < N; i++) {
			int j = N-i;
				sum+=arr[i]*j;
		}
		System.out.println(sum);
	}
	
}
