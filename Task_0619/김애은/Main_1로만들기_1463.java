package algo0619;
/*
 * 풀었던 문제네
 * 1로 만들기 dp같으면서도 dfs같기도 하고 두개가 그게 그건가
 *  <문제 요약>
 * 구해야 하는 것:1로 만들기
 * 제약 사항: 1<=N<10^6
 * 문제 유형: dp
 * 요구 개념: dp
 * 
 * <풀이법>
 * 딱히 풀이법할게 없다
 * 그냥 1구하고 그때 카운트가 전에것보다 작으면 갱신해주기
 */
import java.util.Scanner;

public class Main_1로만들기_1463 {
	static int res=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		sol(N,0);
		System.out.println(res);
	}

	private static void sol(int n, int cnt) {
		if(n==1) {
			if(res>cnt)
				res=cnt;
			return;
		}
		if(n<1)return;
		if(res<cnt) return;
		if(n%3==0)sol(n/3,cnt+1);
		if(n%2==0)sol(n/2,cnt+1);
		sol(n-1,cnt+1);
	}

}
