package 하반기;
/*
 * 투포인터 문제
 * 
 * 처음에 이문제 딱 봤을땐 투포인터라 생각했으나
 * 이중 포문, dp, greedy를 다 시도해봤고...
 * 2시간 걸려서 풀었다.........
 * 
 * 아이디어를 생각하기 힘들었고 개수보다 작은수 값의 변화가 더 큰것이 중요하다는걸 캐치를 못했다.
 * 2중포문이 100억만큼이기 대문에 1초는 불가능했고 다시 생각해보면 되게 쉬운문제였다.
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main_22945_팀빌딩 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		int arr[] = new int[N];
		boolean check[] = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i]=sc.nextInt();
		}
		int start=0;
		int end=N-1;
		int max=0;
		while(start<end) {
			if(arr[start]<arr[end]) {
				max=Math.max(max, arr[start]*(end-start-1));
				start+=1;
			}
			else {
				max=Math.max(max, arr[end]*(end-start-1));
				end-=1;
			}
		}
		System.out.println(max);
	}

}
