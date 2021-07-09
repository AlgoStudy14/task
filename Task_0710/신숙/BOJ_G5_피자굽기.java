import java.util.Scanner;

public class BOJ_G5_피자굽기 {
	/*
	 * <문제>
	 * 오븐의 수?가 D로 주어지고 주어진 N개의 피자를 구우려고한다. 반지름은 제각각.
	 * 피자반죽은 완성되는 순서대로 오븐에 들어가는데, 모두 들어가고 난 뒤, 맨 위 피자가 얼마나 깊이 들어가있는지가 궁금하다..?
	 * 
	 * <문제풀이>
	 * ...? 가끔씩 이해가 안되는 문제가 있다.,..
	 * https://skygood95.tistory.com/89
	 * 이거 보고 이해중...
	 * 피자를 떨궈서 작은거 위에 쌓이는 방식...
	 * 
	 * 
	 */
	static int D, N;
	static int[] oven, pizza;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		D = sc.nextInt();
		N = sc.nextInt();
		oven = new int[D];
		pizza = new int[N];
		
		for(int i = 0; i < D; i++)
			oven[i] = sc.nextInt();
		
		for(int i = 0; i < N; i++)
			pizza[i] = sc.nextInt();
		
		
	}
}
