import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_G5_감소하는수 {
	/*
	 * <문제>
	 * 가장 큰 자리수부터 가장 작은 자리수까지 수가 계속해서 감소한다면 감소하는수(ex. 941)
	 * N이 주어질때, N번째 감소하는 수는?
	 * 
	 * <조건>
	 * 0 <= N <= 1,000,000
	 * 0은 0번째 감소하는 수, 1은 1번째 감소하는 수. N번째 감소하는 수가 없다면 -1 리턴.
	 * 
	 * <풀이>
	 * 수학적 접근, 브루트 포스밖에 생각이 안나는데...  100만번째 일때 백트래킹이 되려면 어떻게 해야할까.
	 * 1부터 100만까지 감소하는 수 일 경우만 숫자 ++ 해가면서 N 과 같아지면 반환하기 밖에 생각이 안남. 
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * https://woongsios.tistory.com/247	<- 참고.
	 * 
	 * <나중에 다시 풀어봐야 함.... 문제의 감이 안잡힘.>
	 */
	
	static int N, ans;
	static ArrayList<Integer> arr = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		if(N <= 10)
			System.out.println(N);
		else if(N > 1022)
			System.out.println(-1);
		else {
			for(int i = 0; i < 10; i++)
				bfs(i, 1);
			Collections.sort(arr);
			System.out.println(arr.get(N));
		}
	}
	
	static void bfs(int start, int n) {
		if(n > 10) {
			return;
		}
		arr.add(start);
		
		for(int i = 0; i < 10; i++) {
			if(start % 10 > i)
				bfs((start * 10) + i, n + 1);
		}
		return;
	}
}