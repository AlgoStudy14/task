import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G5_N단논법 {
	/*
	 * <문제>
	 * a is b, b is c 면 a is c 이다.
	 * 삼단논법으로, N이 나와서 논법 N개, M을 주어지고 논제가 참인지 거짓인지 출력.
	 * 
	 * <문제해결>
	 * 2 <= N <= 26
	 * 각각 연결된 그래프가 있는데, 주어진 점에서 점까지 이어져있는지 확인.
	 * 플로이드 와샬
	 * 
	 * <문제 상황>
	 * 1. a is b 까지는 에러가 없지만, b is c 입력하면 에러뜸.	
	 * 		=> java.util.InputMismatchException	
	 * 		=> 정수로 입력해야 하는데 문자를 입력한 경우 예외 발생 이라는데?? Scanner 문젠가? 그러네 BufferedReader로 바꾸니까 됨.
	 * 2. 근데 d is a가 T가 나옴. 왜지?	=> 출력해보니 모두 1로 되어있음.	=> floyd에서 전부 1이됨	=> map[k][i]로 했었음...
	 * 	
	 */
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		/*Scanner sc = new Scanner(System.in);
		N = sc.nextInt();*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[26][26];
		/*for(int i = 0; i < 26; i++)
			Arrays.fill(map[i], 0);*/
		
		for(int i = 0; i < 26; i++)
			map[i][i] = 1;
		
		for(int i = 0; i < N; i++) {
			//String s = sc.next();
			String s = br.readLine();
			int a = s.charAt(0) - 'a';
			int b = s.charAt(5) - 'a';
			//System.out.println("test " + i + " a = " + a + ", b =  " + b);
			map[a][b] = 1;
		}
		
		floyd();
		
		//M = sc.nextInt();
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			//String s = sc.next();
			String s = br.readLine();
			int a = s.charAt(0) - 'a';
			int b = s.charAt(5) - 'a';
			
			if(map[a][b] == 1)
				System.out.println("T");
			else
				System.out.println("F");
		}
	}
	
	static void floyd() {
		for(int k = 0; k < 26; k++) {
			for(int i = 0; i < 26; i++) {
				for(int j = 0; j < 26; j++) {
					if((map[i][k] == 1) && (map[k][j] == 1))
						map[i][j] = 1;
				}
			}
		}
	}
}
