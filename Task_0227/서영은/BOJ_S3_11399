import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값 
 * 제약 사항: 딱히 없음  
 * 문제 유형: 정렬 
 * 요구 개념: 정렬 
 * 
 * <풀이법 요약>
 * 1. 필요한 시간을 배열로 받아 오름차순으로 정렬한다.
 * 2. 합을 구해 출력한다.
 *
 */
public class BOJ_S3_11399 {
	static int N;
	static int[] p;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N=scann.nextInt();
		
		p=new int[N];
		for (int i = 0; i < N; i++) {
			p[i]=scann.nextInt();
		}
		Arrays.sort(p);
		
		int sum=0;
		int tempSum=0;
		for (int i = 0; i < p.length; i++) {
			tempSum+=p[i];
			sum+=tempSum;
		}
		System.out.println(sum);
	}
}
