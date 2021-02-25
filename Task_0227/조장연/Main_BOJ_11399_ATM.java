import java.util.Arrays;
import java.util.Scanner;

/*
 * <문제 요약>
 * N명의 사람이 ATM을 사용하는 시간이 각각 다를때 모든 사람이 한줄로 서서 각자 사용을 마친시간까지 걸린 시간의 합을 구하기
 * 
 * <풀이법 요약>
 * 어짜피 모든 사람들이 한줄로 서있는 상태에서 시작하기 때문에 걸리는 시간이 가장 빠른 사람부터 사용하는것이 제일 효율이 좋다.
 * 각자 걸리는 시간을 담은 배열을 sorting해줌
 * 문제조건에 맞게 answer 구하기
 */
public class Main_BOJ_11399_ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int answer = 0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			answer += arr[i] * (N - i);
		}
		System.out.print(answer);
	}

}
